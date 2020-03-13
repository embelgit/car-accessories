package com.smt.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import com.smt.bean.CreditCustPaymentDetail;
import com.smt.dao.CreditCustBillDao;
import com.smt.dao.CustomerPaymentDao;
import com.smt.hibernate.CustomerPaymentBean;
import com.smt.utility.HibernateUtility;



public class CustomerPaymentHelper {

	Double bal  ;
	public void regCreditCustomerCashBank(HttpServletRequest request,
			HttpServletResponse response) {



		System.out.println("In helper");
			
			String customer = request.getParameter("customer");
			
			String billNo = request.getParameter("billNo");
			
			String totalAmount = request.getParameter("totalAmount");
			
			String bankName = request.getParameter("bankName");
			
			String custPay = request.getParameter("custPay");
			
			String paymentMode = request.getParameter("paymentMode");
			
			String chequeNum = request.getParameter("chequeNum");
			
			String cardNum = request.getParameter("cardNum");
			
			String accNum = request.getParameter("accNum");
			
			String personname = request.getParameter("personname");
			
			String nameOnCheck = request.getParameter("nameOnCheck");
			
			String paymentType = request.getParameter("paymentType");
			
			
			
			CustomerPaymentBean bean = new CustomerPaymentBean();
			
			
			bean.setCustomer(Long.parseLong(customer));
			bean.setBillNo(Long.parseLong(billNo));
			
			bean.setPersonname(personname);
			
			System.out.println("paymentMode "+paymentMode);
			
			//payment details
			if(paymentMode==null){
				bean.setPaymentMode("N/A");
			}
			else{
				bean.setPaymentMode(paymentMode);
			}
		 
		if(paymentMode.equals("cheque1")){
			
			 if(chequeNum==null){
					bean.setChequeNum("N/A");
				}
				else{
					bean.setChequeNum(chequeNum);
				}
			 
			 if(nameOnCheck==null){
					bean.setNameOnCheck("N/A");
				}
				else{
					bean.setNameOnCheck(nameOnCheck);
				}
			 }
		else if(paymentMode.equals("card1")){
			
				int cardNumLength = cardNum.length();
				 if(cardNumLength > 0){
						
						bean.setCardNum(Long.parseLong(cardNum));
					}
					else{
						bean.setCardNum(null);
					}
		}
	
		else if(paymentMode.equals("neft1")){
			 if(bankName==null){
					bean.setBankName("N/A");
				}
				else{
					bean.setBankName(bankName);
				}
			 
		 int accNumLength = accNum.length();
		 if(accNumLength > 0){
			 bean.setAccNum(Long.parseLong(accNum));	
			
			}
			else{
				 bean.setAccNum(null);
			}
		}	
		 
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			System.out.println(dateFormat1.format(dateobj));
		
			bean.setInsertDate(dateobj);
			HibernateUtility hbu = HibernateUtility.getInstance();
			Session session = hbu.getHibernateSession();
			
			//Query to get latest paid amount
			Query query = session
					.createSQLQuery("SELECT balance ,bill_no from credit_customer_payment WHERE bill_no =:billNo ORDER BY  pk_credit_customer_id  DESC LIMIT 1 ;");
			query.setParameter("billNo",billNo);
			List<Object[]> list = query.list();
			
			System.out.println("Calc total");
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				String newBal = objects[0].toString();
					//BigDecimal bigDecimal = new BigDecimal(newBal);
	                bal= Double.valueOf(newBal);
				System.out.println(bal+"  bal");
				System.out.println("Calc balance");
			}
			
	
		
			if (bal==null) {
				
                Double balance = Double.parseDouble(totalAmount);
				
				if(paymentType.equals("credit"))
				{
					Double newBal = balance - Double.parseDouble(custPay);
					
					bean.setBalance(newBal);
					bean.setPaymentType(paymentType);
					bean.setCredit(Double.parseDouble(custPay));
					if(balance.equals(Double.parseDouble(custPay))){
						CreditCustBillDao Npay = new CreditCustBillDao();
						Npay.DeActivePaymentDone(billNo);
						System.out.println("deactivation done");
					}
				}
				
				else{
					if(paymentType.equals("debit"))
					{
						Double newBal = balance + Double.parseDouble(custPay);
						
						bean.setBalance(newBal);
						bean.setPaymentType(paymentType);
						bean.setCredit(Double.parseDouble(custPay));
					}
					else{
					
					bean.setCredit(0.0);
					}
				}
				
				
				
					bean.setTotalAmount(Double.parseDouble(totalAmount));
					
			com.smt.dao.CustomerPaymentDao dao = new CustomerPaymentDao();
			dao.regCustomerPayment(bean);
			}
			
			else{
				
                Double balance = Double.parseDouble(totalAmount);
				
				
				
				if(paymentType.equals("credit"))
				{
					Double newBal = bal - Double.parseDouble(custPay);
					
					bean.setBalance(newBal);
					bean.setPaymentType(paymentType);
					bean.setCredit(Double.parseDouble(custPay));
					if(bal.equals(Double.parseDouble(custPay))){
						CreditCustBillDao Npay = new CreditCustBillDao();
						Npay.DeActivePaymentDone(billNo);
					}
				}
				
				else{
					if(paymentType.equals("debit"))
					{
						Double newBal = bal + Double.parseDouble(custPay);
						/*System.out.println("debit balance = "+supPay);
						System.out.println("current balace = "+balance);
						System.out.println("debit balance = " +newBal);*/
						bean.setBalance(newBal);
						bean.setPaymentType(paymentType);
						bean.setCredit(Double.parseDouble(custPay));
					}
					else{
					
					bean.setCredit(0.0);
					}
				}

				bean.setTotalAmount(Double.parseDouble(totalAmount));
				
				CustomerPaymentDao dao = new CustomerPaymentDao();
				dao.regCustomerPayment(bean);
				
			}
			
		
		
		
	}
	public List getCustDetailsByDate(HttpServletRequest request,
			HttpServletResponse response) {
		
		String fDate = request.getParameter("fisDate");
        String tDate = request.getParameter("endDate");
		
        Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();
		
        CustomerPaymentDao dao = new CustomerPaymentDao();
		List<CreditCustPaymentDetail> custList = dao.getCreditCustomerDetailsDateWise(fDate,tDate);
		
		
		return custList;
	}
	public List getCustPaymentDetailsBySingleDate(HttpServletRequest request,
			HttpServletResponse response) {
	
			String fDate = request.getParameter("fDate");
			
	         Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();
	 		
	         CustomerPaymentDao dao = new CustomerPaymentDao();
	 		List<CreditCustPaymentDetail> cust1List = dao.getCreditCustPaymentDetailsForSingleDate(fDate);
	 		
	 		
	 		return cust1List;
	}
	public List getCustPaymentDetailsByBill(HttpServletRequest request,
			HttpServletResponse response) {

		
		String billNo = request.getParameter("billNo");
		String fkCustomerId = request.getParameter("fkCustomerId");
		
		
         Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();
 		
         CustomerPaymentDao dao = new CustomerPaymentDao();
 		List<CreditCustPaymentDetail> cust1List = dao.getCreditCustPaymentDetailsAsPerBillNum(billNo,fkCustomerId);
 		
 		
 		return cust1List;

	}
	public List getCustPaymentDetailsByNames(HttpServletRequest request,
			HttpServletResponse response) {
		String fkCustomerId = request.getParameter("fkCustomerId");
		
        Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();
		
        CustomerPaymentDao dao = new CustomerPaymentDao();
		List<CreditCustPaymentDetail> cust1List = dao.getCreditCustPaymentDetailsAsPerName(fkCustomerId);
		
		
		return cust1List;
	}

}
