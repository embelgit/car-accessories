package com.smt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.BarrelEntryBean;
import com.smt.bean.ItemList;
import com.smt.bean.ProductNameBean;
import com.smt.bean.UpdateProductDetails;
import com.smt.hibernate.Category;
import com.smt.hibernate.ProductRegister;
import com.smt.hibernate.ServiceDetail;
import com.smt.utility.HibernateUtility;

public class ProductDetailDao {

	
	
	/*public List getAllProductNames(){
		HibernateUtility hbu =null;
		Session session =null;
		List<ItemList> itemList=null;
		
		try{
		 hbu = HibernateUtility.getInstance();
		 	session = hbu.getHibernateSession();
		Query query = session.createQuery("select itemName , pkProductId from ProductDetail group by itemName");
		List<Object[]>  list=query.list();
		 itemList=new ArrayList<ItemList>(0);
		
		for (Object[] object : list) {
			
			 ItemList subBean=new ItemList();
			 subBean.setItem_name(object[0].toString());
			 subBean.setPk_item_id(Long.parseLong(object[1].toString()));
			 itemList.add(subBean);
		}
		}catch(RuntimeException e){
			
			Log.error("Error in getAllProductNames Method", e);
		}
		finally{
		hbu.closeSession(session);
		}
		return itemList;
	}*/
	
   public List getAllProductDetails()
   {
	   
	   HibernateUtility hbu = null;
	   Session session = null;
	   Query query = null ;
	   List<Object[]> list = null;
	   List<UpdateProductDetails> proList = null;
	   
	   try {
		
		   hbu = HibernateUtility.getInstance();
		   session = hbu.getHibernateSession();
		   query = session.createSQLQuery("select s.shop_name , sup.supplier_name , c.category_name ,sc.subcat_name ,p.pk_product_id, p.item_name , p.is_vat , p.vat_percentage , p.is_alternate_product , p.is_item , p.commision from product_details p left join shop_detail s ON p.fk_shop_id=s.shop_id left join supplier_details sup ON p.fk_vendor_id = sup.supplier_id left join categories c ON p.fk_cat_id = c.pk_category_id left join sub_categories sc ON sc.fk_rootcat_id = c.pk_category_id");
		   list = query.list();
		   proList = new ArrayList<UpdateProductDetails>(0);
		   
		   for (Object[] objects : list) {
				 
			     UpdateProductDetails productDetails  = new UpdateProductDetails();
			     productDetails.setShopName(objects[0].toString());
			     productDetails.setSupplierName(objects[1].toString());
			     productDetails.setCategoryName(objects[2].toString());
			     productDetails.setSubCatName(objects[3].toString());
			     productDetails.setProductId(Long.parseLong(objects[4].toString()));
			     productDetails.setProductName(objects[5].toString());
			     productDetails.setIsVat(objects[6].toString());
			     productDetails.setVatPercentage(Double.parseDouble(objects[7].toString()));
			     productDetails.setIsAlterNate(objects[8].toString());
			     productDetails.setIsItem(objects[9].toString());
			     productDetails.setCommission(Double.parseDouble(objects[10].toString()));
			     
			     proList.add(productDetails);
			    	
				}
		   
		   
	   } catch (Exception e) {
		   Log.error("Error in getAllProductDetails Method ", e);
		// TODO: handle exception
	}
	   finally
	   
	   {
		   if (session!=null) {
			hbu.closeSession(session);
		}
	   }
	return proList;
	   
	   
   }
	
  
   
   public List getLeafcatBYCatandSubCategory(String catID,String subCatID)
	{
	   HibernateUtility hbu = null ; 
	   Session session = null ;
	   Query query = null; 
	   List list = null ;
	   try {
		
		   hbu = HibernateUtility.getInstance();
		   session = hbu.getHibernateSession();
		   query =  session.createQuery("select i.leafcatName from ItemDetail as i join i.productDetail where i.productDetail.category.subCategory.pkSubcatId="+subCatID);
		   list = query.list();
	   } catch (RuntimeException e) {
		   Log.error("Error in getLeafcatBYCatandSubCategory", e);
		// TODO: handle exception
	}
	   finally{
		   if (session!=null) {
			
			   hbu.closeSession(session);
		}
	   }
		
	return list;
		
	}
public List getAllProductName(String productId)
{  
	HibernateUtility hbu = null;
	Session session = null;
	Query query = null;
	List list = null;
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query = session.createSQLQuery("select p.item_name,p.pk_product_id from product_details p where p.pk_product_id="+productId);
		list = query.list();
	} catch (RuntimeException e) {
		
		Log.error("Error In getAllProductname", e);
		// TODO: handle exception
	}
	finally
	{
		if (session!=null) {
			hbu.closeSession(session);
			
		}
	}
	return list;
}
   
/******code for edit item details *****/

public List getallItemDetails(String productId)
{
	HibernateUtility hbu = null;
	Session session = null;
	Query query = null;
	List list = null;
	
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query = session.createSQLQuery("select i.item_name,i.color,i.size,i.sale_price,i.buy_price,i.mmcc,i.pk_item_id from item_details i where i.pk_item_id="+productId);
		list = query.list();
	} catch (RuntimeException e) {
		Log.error("Error in getallItemDetails", e);
		// TODO: handle exception
	}
	finally
	{
		if (session!=null) {
			hbu.closeSession(session);
			
		}
	}
	return list; 

}

public void doProductRegister(ProductRegister pdreg) {
	
	
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
		Category supdetail = (Category) session.load(Category.class, fkCategoryId);
		pdreg.setCategory(supdetail);
		
	
		
		session.save(pdreg);
		
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

    

    public List getAllItemName(){
	
	HibernateUtility hbu = null;
	Session session =  null;
	Query query = null;
	List<Object[]> list = null;
	List<ProductNameBean> itemList = null;
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query = session.createSQLQuery("select p.ProductName, c.category_name, p.HsnSacNo,p.modelName from product_reg p left join categories c ON p.FkCatId = c.pk_category_id");
		list = query.list();
		
		itemList = new ArrayList<ProductNameBean>(0);
		   
		   for (Object[] objects : list) {
				 
			     ProductNameBean item  = new ProductNameBean();
			     item.setItemName(objects[0].toString());
			     item.setCaregoryName(objects[1].toString());
			     item.setHsnsacno(objects[2].toString());
			     item.setModelName(objects[3].toString());
			     
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
 // to get itemName And Category Name on Product Detail Form
	public List getAllItemNameAndCatName() {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createQuery("from ProductRegister");
		 list = query.list();
		}
			catch(RuntimeException e){	
				Log.error("Error in getAllMainCategories()", e);
		}
			finally
			{
					if(session!=null){
					hbu.closeSession(session);
				}
			}
		
	return list;
	}

	
	// get all main Item name 
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
		 Query query2 = session.createSQLQuery("select p.ProductName, c.category_name, p.HsnSacNo from product_reg p left join categories c ON p.FkCatId = c.pk_category_id");
			/*query2.setParameter("stDate", fDate);
	        query2.setParameter("edDate", tDate);*/
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<ItemList>(0);
			
			
			for (Object[] object : list) {
				k++;	
				ItemList reports = new ItemList();
				reports.setSerialnumber(k);
				reports.setItem_name(object[0].toString());
				reports.setCategoryName(object[1].toString());
				reports.setHsnsacno(object[2].toString());
								
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


	public List getProductNames()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		 List list = null;
	 try {
			 hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 query = session.createQuery("from ProductRegister");
			 list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupllier", e);
		}
		 
		 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
				return list;
		
	}
	
	
	public List getAllProductSetailsForEdit(String ProductId) {


		System.out.println("into dao supplier : "+ProductId);
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		 List list = null;
		 try {
			 hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 query = session.createSQLQuery("select ProductName, Vat, modelName, pkProductNameId, HsnSacNo from product_reg where pkProductNameId ="+ProductId);
			 list = query.list(); 
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		 
		 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
		 System.out.println("out of dao - return credit customer List : "+list);
				return list;
	}
	
	/* To Register ItemName */
	public void registerItemName(ServiceDetail pod) {
		
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;
		
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 transaction = session.beginTransaction();
	
		
		session.save(pod);
		transaction.commit();
		}
		
		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(Exception rbe)
			{
				rbe.printStackTrace();
			}	
		}finally{
		hbu.closeSession(session);
		}
		
	}
	
	public List getProductNames1()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		 List list = null;
	 try {
			 hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 query = session.createQuery("from ServiceDetail");
			 list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupllier", e);
		}
		 
		 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
				return list;
		
	}
	

    public List getAllItemNameOil(){
	
	HibernateUtility hbu = null;
	Session session =  null;
	Query query = null;
	List<Object[]> list = null;
	List<BarrelEntryBean> itemList = null;
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query = session.createSQLQuery("select p.ProductName, c.category_name, p.HsnSacNo,p.modelName from barrelentry p left join categories c ON p.FkCatId = c.pk_category_id");
		list = query.list();
		
		itemList = new ArrayList<BarrelEntryBean>(0);
		   
		   for (Object[] objects : list) {
				 
			   BarrelEntryBean item  = new BarrelEntryBean();
			     item.setItemName(objects[0].toString());
			     System.out.println("ItemName++++++++++++"+objects[0].toString());
			     item.setCategoryName(objects[1].toString());
			     item.setHsnsacno(objects[2].toString());
			     item.setModelName(objects[3].toString());
			     
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
   
}
