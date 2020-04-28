package com.smt.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.BarrelEntryBean;
import com.smt.bean.CategoryWisePurchase;
import com.smt.bean.CustomerBean;
import com.smt.bean.ItemList;
import com.smt.hibernate.BarrelBillingHibernate;
import com.smt.hibernate.BarrelEntryHibernate;
import com.smt.hibernate.Category;
import com.smt.hibernate.GoodReceive;
import com.smt.hibernate.GoodsReceiveBarrelHibernate;
import com.smt.hibernate.OtherBill;
import com.smt.utility.HibernateUtility;

public class BarrelEntryDao {
	
	
	public void doProductRegister(BarrelEntryHibernate pdreg) {
		
		
		HibernateUtility hbu = null;
		Session session = null;
		org.hibernate.Transaction transaction = null;
		
		Category category = null;
		Long fkCategoryId = null;
		
		try {
			
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
			
			
			  fkCategoryId = pdreg.getFkCategoryId(); 
			  Category supdetail =(Category) session.load(Category.class, fkCategoryId);
			   pdreg.setCategory(supdetail);
			 
			 
			
			session.save(pdreg);
			System.out.println("Data in dao sucessfully saved");
			
			
			transaction.commit();
		} catch (RuntimeException e) {
			
			try {
				transaction.rollback();
			} catch (RuntimeException e2) {
				Log.error("Error in PRODUCTDETAIL Add FORM", e2);
			}
		}
		
		finally
		{
			if(session!=null)
			{
				hbu.closeSession(session);
			}
		}
		
		
	   }
	
/////////////
    public List getAllItemNameOil(){
	
	HibernateUtility hbu = null;
	Session session =  null;
	Query query = null;
	List<Object[]> list = null;
	List<BarrelEntryBean> itemList = null;
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query = session.createSQLQuery("select p.pkProductNameId,p.ProductName, c.category_name, p.HsnSacNo,p.modelName from barrelentry p left join categories c ON p.FkCatId = c.pk_category_id");
		list = query.list();
		
		itemList = new ArrayList<BarrelEntryBean>(0);
		   
		   for (Object[] objects : list) {
				 
			   BarrelEntryBean item  = new BarrelEntryBean();
			   
			     item.setPkProductId(Long.parseLong(objects[0].toString()));
			     item.setItemName(objects[1].toString());
			     System.out.println("ItemName++++++++++++"+objects[0].toString());
			     item.setCategoryName(objects[2].toString());
			     item.setHsnsacno(objects[3].toString());
			     item.setModelName(objects[4].toString());
			     
			     itemList.add(item);
			    	
				}
		
	} catch (RuntimeException e) {
		Log.error("Error in getAllSupplier ", e);
	}
	finally
	{
		if (session!=null) {
			hbu.closeSession(session);
		}
	}
	
	return itemList;
  }

    public void regGoodReceive(GoodsReceiveBarrelHibernate gd) {
		// TODO Auto-generated method stub
		
		
		HibernateUtility hbu = null ;
		Session session  = null;
		Transaction transaction = null;
		
		
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
//			System.out.println(Arrays.toString(gd));
			System.out.println("done");
			session.save(gd);
			System.out.println("successfully done");
			transaction.commit();
			
		
		} catch (Exception e) {
			try {
				transaction.rollback();
			} catch (RuntimeException ede) {
			     
			//	Log.error("Error in transaction", ede);
			}
		}
		
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
				
			}
		}
		
	}
    
    /////good receive barrel item list grid/////////
    

	
	public List getAllItemDetails1(String key) {
		HibernateUtility hbu = null;
		Session session = null;
		List<BarrelEntryBean> itemlist = null;
		List<Object[]> list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			
			//Query query = session.createSQLQuery("SELECT p.ItemName, p.PkProNameId, p.hsnsacNo, stock.Quntity, se.PricePerUnit from productname p  inner join stock INNER JOIN stockentry se   on p.PkProNameId = stock.Fk_ItemName_Id and se.fk_product_reg = p.PkProNameId AND p.PkProNameId="+ key);
			Query query = session.createSQLQuery("SELECT ProductName,Vat,HsnSacNo,category_name,NoOfBarrel,perlitre,TotalLitre FROM barrelentry WHERE pkProductNameId="+key);
			 list = query.list();
			itemlist = new ArrayList<BarrelEntryBean>(0);
			for (Object[] objects : list) {
				BarrelEntryBean bean = new BarrelEntryBean();
				bean.setItemName(objects[0].toString());
				bean.setVat(Double.parseDouble(objects[1].toString()));
				bean.setHsnsacno(objects[2].toString());
				bean.setCategoryName(objects[3].toString());
				bean.setNumberofBarrel(Double.parseDouble(objects[4].toString()));
				bean.setOilperlitre(Double.parseDouble(objects[5].toString()));
				bean.setTotalLitre(Double.parseDouble(objects[6].toString()));
				
				//System.out.println("Total Weight=======" +bean.getTotelWeight());
				
				//bean.setVat(0d);
				bean.setIgst(0d);
				System.out.println(Arrays.toString(objects));
				System.out.println("Done___=+++++=");
				
				
				
				itemlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemlist;
	}
	
	
	
	//////////Billing product grid oil////////////
	
	
	public List<BarrelEntryBean> getProductInGridBillingOil(String productId) {
		// TODO Auto-generated method stub
		
		HibernateUtility hbu=null;
		Session session=null;
		
		List<BarrelEntryBean> itemlist=null;
		try
		{
			
			    System.out.println("priya");
				hbu = HibernateUtility.getInstance();
		        session = hbu.getHibernateSession();
		

		 Query query=session.createSQLQuery("SELECT ItemName,PkGoodRecId, CategoryName ,hsnsacno, vat, igst,salePrice,TotalLitre,NoOfBarrel FROM goodreceivebarrel WHERE ItemName ='"+productId+"'");
			List<Object[]> list = query.list();

			 itemlist = new ArrayList<BarrelEntryBean>(0);
		     for (Object[] objects : list) {
			 System.out.println(Arrays.toString(objects));
			 BarrelEntryBean bean = new BarrelEntryBean();
			  
			 bean.setItemName(objects[0].toString());
			 bean.setPkProductId(Long.parseLong(objects[1].toString()));
			 bean.setCategoryName(objects[2].toString());
			 //bean.setBarcodeNo(Long.parseLong(objects[3].toString()));
			 bean.setHsnsacno(objects[3].toString());
			 bean.setQuantity(0l);
			 bean.setSalePrice(Double.parseDouble(objects[6].toString()));
			 bean.setDiscount(0d);
			 bean.setDiscountAmt(0d);
			 bean.setVat(Double.parseDouble(objects[4].toString()));
			 bean.setIgst(Double.parseDouble(objects[5].toString()));
			 bean.setTaxAmt(0d);
			 bean.setTotalLitre(Double.parseDouble(objects[7].toString()));
			 bean.setNumberofBarrel(Double.parseDouble(objects[8].toString()));

			 
			 itemlist.add(bean);
		     }
		     }
				catch(RuntimeException e)
				{
					Log.error("Error in getAllItemDetails(String key)", e);	
				}finally
				{if(session!=null){
					hbu.closeSession(session);	
				}
				}
		return itemlist;
		}
	
	///register in DB
	public void registerBill(BarrelBillingHibernate cust) {
		// TODO Auto-generated method stub
		HibernateUtility hbu = null ;
		Session session  = null;
		Transaction transaction = null;
		
		
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
			
			session.save(cust);
			transaction.commit();
			
		
		} catch (Exception e) {
			try {
				transaction.rollback();
			} catch (RuntimeException ede) {
			     
			//	Log.error("Error in transaction", ede);
			}
		}
		
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
				
			}
		}
	}
	
	// get all main Item name for barrel entry form
		public List<ItemList> getAllMAinItem() {
			// TODO Auto-generated method stub
			HibernateUtility hbu=null;
			Session session=null;
			List<ItemList> catList=null;
			try
			{
				Long k = 0l;
				hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 Query query2 = session.createSQLQuery("select ProductName,category_name,HsnSacNo,TotalLitre,NoOfBarrel,modelName FROM barrelentry");
				
		        List<Object[]> list = query2.list();
		        catList= new ArrayList<ItemList>(0);
				
				
				for (Object[] object : list) {
					k++;	
					ItemList reports = new ItemList();
					reports.setSerialnumber(k);
					reports.setItem_name(object[0].toString());
					reports.setCategoryName(object[1].toString());
					reports.setHsnsacno(object[2].toString());
					reports.setTotalLitre(Double.parseDouble(object[3].toString()));
					reports.setNumberofBarrel(Double.parseDouble(object[4].toString()));
					reports.setModelName(object[5].toString());
									
					catList.add(reports); 
			
				}}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return catList;
		}
	
}
