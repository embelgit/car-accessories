package com.smt.hibernate;

import java.util.Date;

public class CarEntry {
	
	private Long pkCarEntryId;
	private String carNo;
	private Long contactNo;
	private String ownerName;
	private char activeYN;
	private Date dateid;
	private Double KmReader;
	private String vehiclecolor;
	private String vehiclename;
	
	
	public Double getKmReader() {
		return KmReader;
	}


	public void setKmReader(Double kmReader) {
		KmReader = kmReader;
	}


	public String getVehiclename() {
		return vehiclename;
	}


	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}

	
	public String getVehiclecolor() {
		return vehiclecolor;
	}


	public void setVehiclecolor(String vehiclecolor) {
		this.vehiclecolor = vehiclecolor;
	}


	public CarEntry(Long pkCarEntryId, String carNo, Long contactNo,
			String ownerName, char activeYN, Date dateid) {
		super();
		this.pkCarEntryId = pkCarEntryId;
		this.carNo = carNo;
		this.contactNo = contactNo;
		this.ownerName = ownerName;
		this.activeYN = activeYN;
		this.dateid = dateid;
	}


	public CarEntry() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getPkCarEntryId() {
		return pkCarEntryId;
	}


	public void setPkCarEntryId(Long pkCarEntryId) {
		this.pkCarEntryId = pkCarEntryId;
	}


	public String getCarNo() {
		return carNo;
	}


	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}


	public Long getContactNo() {
		return contactNo;
	}


	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public char getActiveYN() {
		return activeYN;
	}


	public void setActiveYN(char activeYN) {
		this.activeYN = activeYN;
	}


	public Date getDateid() {
		return dateid;
	}


	public void setDateid(Date dateid) {
		this.dateid = dateid;
	}
	
	
	

	
	
}
