package com.smt.helper;


import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.GoodReceiveItemBean;
import com.smt.bean.ItemList;
import com.smt.bean.ProductDetailsToEdit;
import com.smt.dao.ProductDetailDao;
import com.smt.hibernate.ProductRegister;
import com.smt.hibernate.ServiceDetail;
import com.smt.utility.HibernateUtility;

public class ProductDetailHelper {

		
	/*//
	public List getAllProductNames(){
		
		ProductDetailDao pdao=new ProductDetailDao();
		return pdao.getAllProductNames();
		
	}*/
	
	
	
	public void doProductRegister(HttpServletRequest request,
			HttpServletResponse response) {
		
		String itemName = request.getParameter("itemName");
		String catId = request.getParameter("catId");
		
		String modelName = request.getParameter("modelName");
		String hsnsacno = request.getParameter("hsnsacno");
		
		String categoryName = request.getParameter("categoryName");
		
		ProductRegister pdreg = new ProductRegister();
		
		pdreg.setItemName(itemName);
		pdreg.setCategoryName(categoryName);
		pdreg.setFkCategoryId(Long.parseLong(catId));
		
		if (!"".equals(modelName)) 
		{
			pdreg.setModelName(modelName);
		} 
		else 
		{
			pdreg.setModelName("N/A");
		}
		
		pdreg.setVat(0.0);
		pdreg.setHsnsacno(hsnsacno);
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date dateobj = new Date();
		
		String newDate = df.format(dateobj);
		pdreg.setIsInsertDate(dateobj);
		
		ProductDetailDao reg = new ProductDetailDao();
		reg.doProductRegister(pdreg);
		
		
	}
	
	// to get all Item Name on Good receive form
	
	public List getAllItemName()
	{
		ProductDetailDao dow = new ProductDetailDao();
		return dow.getAllItemName();
	}
    // to get itemName And Category Name on Product Detail Form
	public List getAllItemNameAndCatName()
	{
		ProductDetailDao dow = new ProductDetailDao();
		return dow.getAllItemNameAndCatName();
	}
	
	// to get itemName And Category Name on good receive form Form
	public GoodReceiveItemBean getDetailsById(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String itemName = request.getParameter("itemName");
		String categoryName = request.getParameter("catName");
		String hsnsacno = request.getParameter("hsnsacno");
		
		GoodReceiveItemBean bean = new GoodReceiveItemBean();
		bean.setItemName(itemName);
		bean.setCatName(categoryName);
		bean.setHsnsacno(hsnsacno);
		bean.setQuantity(0);
		bean.setBuyPrice(0d);
		bean.setSalePrice(0d);
		
		return bean;
	}


    //get all main product Name
	public List getAllMAinItem(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		 Map<Long,ItemList> map = new HashMap<Long,ItemList>();
			
		    ProductDetailDao dao = new ProductDetailDao();
			List<ItemList> exp1List = dao.getAllMAinItem();
			
			return exp1List;
	}

	
	public Map getProductDetailsForEdit(String productId) {
		
	 	System.out.println("into helper class");
	 	ProductDetailDao dao1 = new ProductDetailDao();
		List catList = dao1.getAllProductSetailsForEdit(productId);
		
		Map  map =  new HashMap();
		for(int i=0;i<catList.size();i++)
		{
			Object[] o = (Object[])catList.get(i);
		
			ProductDetailsToEdit bean = new ProductDetailsToEdit();
			bean.setProName(o[0].toString());
			String vat = o[1].toString();
			bean.setVat(Double.parseDouble(vat));
			bean.setModelName(o[2].toString());
			String pkId = o[3].toString();
			bean.setHsnsacno(o[4].toString());
			bean.setPkProduct(Long.parseLong(pkId));
			
			map.put(bean.getPkProduct(),bean);
		}
		System.out.println("out of helper return map : "+map);
		return map;
	}
	
	public void editProductDetail(HttpServletRequest request,
			HttpServletResponse response) {
	
		String productId = request.getParameter("productId");
		
		String itemName = request.getParameter("itemName");
		
		String modelName = request.getParameter("modelName");
		String hsnsacno = request.getParameter("hsnsacno");
		
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;
		
		 hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		 transaction = session.beginTransaction();
	
		//long customerId = Long.parseLong(customerId);
		 System.out.println("%%%%%%%%%%%%%%%%% Product id :"+productId);
		long productID =Long.parseLong(productId);
		ProductRegister det = (ProductRegister) session.get(ProductRegister.class, productID);
		
		if (!"".equals(itemName)) {
			det.setItemName(itemName);
		} else {
			det.setItemName("N/A");
		}
		
		
		det.setVat(0d);
		
		
		if (!"".equals(modelName)) {
			det.setModelName(modelName);
		} else {
			det.setModelName("N/A");
		}
		det.setHsnsacno(hsnsacno);
	    session.saveOrUpdate(det);
		transaction.commit();
		session.close();
		System.out.println("Record updated successfully.");
	
	
	
	}
	

	public void registerItemName(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		String itemName = request.getParameter("itemName");
		String hsnsacNo = request.getParameter("hsnsacNo");
		
		ServiceDetail pdreg = new ServiceDetail();
		
		if(!"".equals(hsnsacNo))
		
		{
			pdreg.setHsnsacno(hsnsacNo);
	    } 
		else
	    {
			pdreg.setHsnsacno("N/A");
	    }
		
		
		pdreg.setItemName(itemName);
		
		
		ProductDetailDao reg = new ProductDetailDao();
		reg.registerItemName(pdreg);
		
	}
	
}