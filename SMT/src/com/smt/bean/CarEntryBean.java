package com.smt.bean;

import java.util.Date;

public class CarEntryBean {
	

	private Long pkCarEntryId;
	private String carNo;
	private Long contactNo;
	private String ownerName;
	private char activeYN;
	private Date dateid;
	private Long KmReader;
	private String vehiclecolor;
	private String active;
	private String date;
	private Double KmReader1;
	
	
	public Double getKmReader1() {
		return KmReader1;
	}
	public void setKmReader1(Double kmReader1) {
		KmReader1 = kmReader1;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
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
	public Long getKmReader() {
		return KmReader;
	}
	public void setKmReader(Long kmReader) {
		KmReader = kmReader;
	}
	public String getVehiclecolor() {
		return vehiclecolor;
	}
	public void setVehiclecolor(String vehiclecolor) {
		this.vehiclecolor = vehiclecolor;
	}
	
	
	

}
