package com.smt.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smt.dao.BarrelEntryDao;
import com.smt.dao.ProductDetailDao;
import com.smt.hibernate.BarrelEntryHibernate;
import com.smt.hibernate.ProductRegister;

public class BarrelEntryHelper {
	
	
	
	public void doProductRegister(HttpServletRequest request,
			HttpServletResponse response) {
		
		String itemName = request.getParameter("itemName");
		String catId = request.getParameter("catId");
		String modelName = request.getParameter("modelName");
		String hsnsacno = request.getParameter("hsnsacno");
		String categoryName = request.getParameter("categoryName");
		String NoBarrel = request.getParameter("NoBarrel");
		String perlitre = request.getParameter("perlitre");
		String TotalBarrel = request.getParameter("TotalBarrel");
		
		
		
       BarrelEntryHibernate pdreg = new BarrelEntryHibernate();
		
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
		pdreg.setNumberofBarrel(Double.parseDouble(NoBarrel));
		pdreg.setOilperlitre(Double.parseDouble(perlitre));
		pdreg.setTotalLitre(Double.parseDouble(TotalBarrel));
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date dateobj = new Date();
		
		String newDate = df.format(dateobj);
		pdreg.setIsInsertDate(dateobj);
		
		BarrelEntryDao reg = new BarrelEntryDao();
		reg.doProductRegister(pdreg);
		
		
	}

}
