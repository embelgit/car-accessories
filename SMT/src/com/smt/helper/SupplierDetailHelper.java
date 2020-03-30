package com.smt.helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.CreditCustPaymentDetail;
import com.smt.bean.GetSupplierDetails;
import com.smt.bean.PurchaseReturnGetItems;
import com.smt.bean.SupplierEditBean;
import com.smt.dao.SupplierDetailDao;
import com.smt.hibernate.SupplierDetail;
import com.smt.utility.HibernateUtility;

public class SupplierDetailHelper {

	
	public  void doSupplier(HttpServletRequest request , HttpServletResponse response )
	{
		
		
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String contactPerson = request.getParameter("contactPerson");
		String email = request.getParameter("email");
		String pin = request.getParameter("pin");
		String supplierName = request.getParameter("supplierName");
		String mobileno = request.getParameter("mobileno");
		String panNo = request.getParameter("panNo");
		
		SupplierDetail detail = new SupplierDetail();
		
		if(!"".equals(address)){
			
			detail.setAddress(address);
		}
		else{
			detail.setAddress("N/A");
		}
		
		
		
		
		
		if(!"".equals(city)){
			
			detail.setCity(city);	
		}
		else
		{
			
			detail.setCity("N/A");
		}
	    
	    
		if(!"".equals(contactPerson)){
			
			detail.setContactPerson(contactPerson);	
		}
		else
		{
			
			detail.setContactPerson("N/A");
		}
	    
		
		
		
		if(!"".equals(email))
		{
			
			detail.setEmail(email);	
		}
		else
		{
			detail.setEmail("N/A");
		}
		
		
	    
		
		if(!"".equals(pin)){
			
			detail.setPin(Long.parseLong(pin));
		}
		else
		{
			detail.setPin(Long.parseLong("00"));
		}
		
		
		if(!"".equals(panNo)){
			
			detail.setPanNo(panNo);
		}
		else{
			
			detail.setPanNo("NA");
		}
	    detail.setSupplierName(supplierName);
	    
	    if(!"".equals(mobileno)){
	    	
	    	detail.setMobileno(Long.parseLong(mobileno));
	    }
	    else{
	    	
	    	detail.setMobileno(Long.parseLong("00"));
	    }
	    
	   
	    SupplierDetailDao dao = new SupplierDetailDao();
	    dao.valSupplierDetail(detail);
	
	}
	
	
	
	//Edit Supplier Detail
	public  void editSupplier(HttpServletRequest request , HttpServletResponse response )
	{
		
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String contactPerson = request.getParameter("contactPerson");
		String email = request.getParameter("email");
		String pin = request.getParameter("pin");
		String mobileno = request.getParameter("mobileno");
		String panNo=request.getParameter("panNo");
		String supplierName1 = request.getParameter("supplierName1");
		String supplierName = request.getParameter("supplierName");
		
		SupplierDetail de = new SupplierDetail();
		
	    de.setSupplierName(supplierName1);
		de.setAddress(address);
	    de.setCity(city);
	    de.setContactPerson(contactPerson);
	    de.setEmail(email);
	    de.setMobileno(Long.parseLong(mobileno));
	    de.setPanNo(panNo);
		
		HibernateUtility hbu = HibernateUtility.getInstance();
		Session session = hbu.getHibernateSession();
		Transaction transaction = session.beginTransaction();
		
		SupplierDetail det = (SupplierDetail) session.get(SupplierDetail.class, new Long(supplierName));
		
		det.setSupplierName(supplierName1);
		det.setAddress(address);
	    det.setCity(city);
	    det.setContactPerson(contactPerson);
	    det.setEmail(email);
	    det.setPin(Long.parseLong(pin));
	    det.setMobileno(Long.parseLong(mobileno));
	    det.setPanNo(panNo);
	    
	    session.saveOrUpdate(det);
		transaction.commit();
	
	}
	
	
	
	
	
	
	public List getAllSuppliers()
	{
		SupplierDetailDao dao = new SupplierDetailDao();
		return dao.getAllMainSuppliers();
	}

	
	// get all Information about Supplier on SupplierEdit Form
	public Map getEditSupplier(Long suppilerId) {
		
		SupplierDetailDao dao1 = new SupplierDetailDao();
		List catList = dao1.getEditSupplier1(suppilerId);
		
		Map  map =  new HashMap();
		for(int i=0;i<catList.size();i++)
		{
			Object[] o = (Object[])catList.get(i);
			SupplierEditBean bean = new SupplierEditBean();
			bean.setAddress((String)o[0]);
			bean.setCity((String)o[1]);
			bean.setContactPerson((String)o[2]);
			bean.setPin((Long)o[3]);
			bean.setEmail((String)o[4]);
			bean.setMobileno((Long)o[5]);
			bean.setPanNo((String)o[6]);
			bean.setSupplierName((String)o[7]);
			
			map.put(bean.getSupplierName(),bean);
		}
		return map;
	}


// get supplier name
	public List getAllMAinSupp(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		 Map<Long,SupplierDetail> map = new HashMap<Long,SupplierDetail>();
			
		    SupplierDetailDao dao = new SupplierDetailDao();
			List<SupplierDetail> exp1List = dao.getAllMAinSupp();
			
			return exp1List;
	}
	
	public List getSupplier(){
		 
		 SupplierDetailDao dao = new SupplierDetailDao();
			return dao.getSupplierNameByToPay();
	
	 }
	
	public List getEmployee(){
		 
		 SupplierDetailDao dao = new SupplierDetailDao();
			return dao.getEmployeeNameByToPay();
	
	 }
	

	public Map getAllBillBySuppliers(String supplierId) {
		// TODO Auto-generated method stub
		SupplierDetailDao dao = new SupplierDetailDao();
		List list= dao.getAllBillBySuppliers(supplierId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			com.smt.bean.GetSupplierDetails bean = new com.smt.bean.GetSupplierDetails();
			System.out.println(Arrays.toString(o));
			bean.setBillNo(o[0].toString());
			
			//bean.setTotalAmount((Double)o[1]);
			System.out.println("get bill no - "+bean.getBillNo());
			map.put(bean.getBillNo(),bean);
			
		}
		return map;
	}
	
	public List getAllUnPaidBillAmount(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/*SupplierDetailDao dao = new SupplierDetailDao();
		List list= dao.getAllUnPaidBillAmount(supplierId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			com.smt.bean.GetSupplierDetails bean = new com.smt.bean.GetSupplierDetails();
			System.out.println(Arrays.toString(o));
			bean.setBillNo(o[0].toString());
			bean.setTotalAmt(Double.parseDouble(o[1].toString()));
			//bean.setTotalAmount((Double)o[1]);
			System.out.println("***************"+o[0]);
			map.put(bean.getBillNo(),bean);
			
		}
		return map;*/
		
		String supplierId = request.getParameter("supplier");
       
        Map<Long,GetSupplierDetails> map = new HashMap<Long,GetSupplierDetails>();
		
        SupplierDetailDao dao = new SupplierDetailDao();
		List<GetSupplierDetails> custList = dao.getAllUnPaidBillAmount(supplierId);
		
		
		return custList;
	}
	
	public Map getAllBillBySuppliers1(String supplierId) {
		// TODO Auto-generated method stub
		SupplierDetailDao dao = new SupplierDetailDao();
		List list= dao.getAllBillBySuppliers1(supplierId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			com.smt.bean.GetSupplierDetails bean = new com.smt.bean.GetSupplierDetails();
			System.out.println(Arrays.toString(o));
			bean.setBillNo(o[0].toString());
			
			//bean.setTotalAmount((Double)o[1]);
			System.out.println("***************"+o[0]);
			map.put(bean.getBillNo(),bean);
			
		}
		return map;
	}
	
	public Map getTotalItemByBillNo(String billNo, String supplierId) {
		// TODO Auto-generated method stub
		SupplierDetailDao dao = new SupplierDetailDao();
		List list = dao.getTotalItemByBillNo(billNo,supplierId);
		Map  map =  new HashMap();
		
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			PurchaseReturnGetItems bean = new PurchaseReturnGetItems();
			bean.setPkGoodRecId(Long.parseLong(o[0].toString()));
			bean.setCatName(o[1].toString());
			bean.setItemName(o[2].toString());
			bean.setQuantity(Long.parseLong(o[3].toString()));
			bean.setBuyPrice(Double.parseDouble(o[4].toString()));
			bean.setVat(Double.parseDouble(o[5].toString()));
			bean.setTotal(Double.parseDouble(o[6].toString()));
			bean.setContactPerson(o[7].toString());
			bean.setBarcodeNo(Long.parseLong(o[8].toString()));
			bean.setOndate(o[9].toString());
			bean.setAvailquantity(Long.parseLong(o[10].toString()));
			/*bean.setTotalAmt(Double.parseDouble(o[0].toString()));*/
			System.out.println("***************"+o[0]);
			bean.setIgst(Double.parseDouble(o[11].toString()));
			map.put(bean.getPkGoodRecId(),bean);
		}
		return map;
	}
	
	
}
