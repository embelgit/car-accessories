package com.smt.hibernate;

public class VatEntry {
	
	private long vatPkId;
	private String vatName;
	private double vatPercentage;
	
	public long getVatPkId() {
		return vatPkId;
	}
	public void setVatPkId(long vatPkId) {
		this.vatPkId = vatPkId;
	}
	public String getVatName() {
		return vatName;
	}
	public void setVatName(String vatName) {
		this.vatName = vatName;
	}
	public double getVatPercentage() {
		return vatPercentage;
	}
	public void setVatPercentage(double vatPercentage) {
		this.vatPercentage = vatPercentage;
	}
	
	

}
