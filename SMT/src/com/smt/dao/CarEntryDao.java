package com.smt.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.BillCopy;
import com.smt.bean.CarEntryBean;
import com.smt.hibernate.CarEntry;
import com.smt.utility.HibernateUtility;



public class CarEntryDao {

	/*Register Car Entry...  */
	
	public void registerCarEntry(CarEntry car) {
		// TODO Auto-generated method stub
		
		com.smt.utility.HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;
		try{
		hbu = com.smt.utility.HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();
	
		
		session.save(car);
		transaction.commit();
		
		}
		
		/*catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				Log.error("Couldn't roll back tranaction",rbe);
			}	
		}*/finally{
		hbu.closeSession(session);
		}
		
	}
	
	public List getAllCarNo()

	{
		com.smt.utility.HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
		 hbu = com.smt.utility.HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 Date dateobj = new Date();
		 System.out.println("in getAllCarNo() today's date  - "+df.format(dateobj));
		 Query query = session.createQuery("from CarEntry where activeYN='y' and dateid=:dateobj");
		 query.setParameter("dateobj", dateobj); 
		 list = query.list();
		 System.out.println("in getAllCarNo() - query.list.size - "+query.list().size());
		}
		catch(Exception e){	
			e.printStackTrace();
	}
		finally
		{
				if(session!=null){
				hbu.closeSession(session);
			}
		}
		
	return list;
	
	}

	public void updateCarStatus(Long carID) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction = null;
	
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 transaction = session.beginTransaction();
		
		 CarEntry car = (CarEntry) session.get(CarEntry.class, new Long(carID));
		 car.setActiveYN('N');
		 session.saveOrUpdate(car);
		 transaction.commit();
		 
		
		 
		}
			catch(Exception e){	
				e.printStackTrace();
		}
			finally
			{
					if(session!=null){
					hbu.closeSession(session);
				}
			}
		
		
	}	
		
	//get bill nos and customer name to get Bill copy
	
	public List getBillNoAndNames()
	{
		HibernateUtility hbu=null;
		Session session=null;
		
		List<BillCopy> billList=null;
		 List<Object[]> list = null;
		
		try
		{
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				Query query=session.createSQLQuery("select BillNo, OwnerName from customerbill group by BillNo order by BillNo desc;");
				list = query.list();
				billList = new ArrayList<BillCopy>(0);
				
		 for (Object[] objects : list) {
			 BillCopy bean = new BillCopy();
			
			bean.setBillNo(Long.parseLong(objects[0].toString()));
			bean.setCustName((String) objects[1]);
			 
			billList.add(bean);
			}
		 }
		catch(RuntimeException  e)
		{
				
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return billList;
	}
	
	public List getcarDetailReports() {

		HibernateUtility hbu = null;
		Session session = null;
		List<CarEntryBean> saleList = null;
		try {

			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			
			Query query=session.createSQLQuery("select  Car_No,Owner_Name,Contact_No,ActiveYN,OnDate,KmReader,vehiclecolor FROM carentry");
			List<Object[]> list = query.list();

			saleList = new ArrayList<CarEntryBean>(0);

			for (Object[] object : list) {
				CarEntryBean reports = new CarEntryBean();

				System.out.println("RAJJ" + Arrays.toString(object));
				
				reports.setCarNo(object[0].toString());
				reports.setOwnerName(object[1].toString());
				reports.setContactNo(Long.parseLong(object[2].toString()));
				reports.setActive(object[3].toString());
				//reports.setDateid(Date(object[4]));
				reports.setDate(object[4].toString());
				reports.setKmReader1(Double.parseDouble(object[5].toString()));
				reports.setVehiclecolor(object[6].toString());
				
				saleList.add(reports); 

			}
		} catch (RuntimeException e) {

		} finally {

			hbu.closeSession(session);
		}
		return saleList;
	}
	
	
	//get vehicle Number for delete vehicle entry
	
		public List getVehicleNumber()
		{
			HibernateUtility hbu=null;
			Session session=null;
			
			List<CarEntryBean> vehicleList=null;
			 List<Object[]> list = null;
			
			try
			{
					hbu = HibernateUtility.getInstance();
					session = hbu.getHibernateSession();
					Query query=session.createSQLQuery("select pkCarEntryId,Car_No,Owner_Name from carentry");
					list = query.list();
					vehicleList = new ArrayList<CarEntryBean>(0);
					
			 for (Object[] objects : list) {
				 CarEntryBean bean = new CarEntryBean();
				 
				 bean.setPkCarEntryId(Long.parseLong(objects[0].toString()));
				bean.setCarNo(objects[1].toString());
				bean.setOwnerName(objects[2].toString());
				 
				vehicleList.add(bean);
				}
			 }
			catch(RuntimeException  e)
			{
					
			}finally
			{if(session!=null){
				hbu.closeSession(session);	
			}
			}
			return vehicleList;
		}
	
		//Delete of vehicle Entry
		public void deleteVehicle(String vehicleNo) {
			
			HibernateUtility hbu = null ;
			 Session session = null;
			 Transaction tx = null;
			 List list  = null;
			 try {
				 hbu = HibernateUtility.getInstance();
				 session = hbu.getHibernateSession();
				  tx = session.beginTransaction();
					Query query = session.createSQLQuery("delete from carentry where pkCarEntryId="+vehicleNo);
					int seletedRecords = query.executeUpdate();
					tx.commit();
					System.out.println("Number of credit Cusr deleted == + ="+seletedRecords);
					
					
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
				
			 finally
			 {
				 if (session!=null) {
					hbu.closeSession(session);
				}
			 }
			
		}

	
	
	
	

	private Date Date(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	private char charAt(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
