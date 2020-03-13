package com.smt.bean;

public class GoodReceiveItemBean {
	
	private String itemName;
	private String catName;
	private double vat;
	private double igst;
	private String hsnsacno;
	private double sgst;
	private double salePrice;
	private double discount;
	
	
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public double getVat() {
		return vat;
	}
	public void setVat(double vat) {
		this.vat = vat;
	}
	public double getIgst() {
		return igst;
	}
	public void setIgst(double igst) {
		this.igst = igst;
	}
	public String getHsnsacno() {
		return hsnsacno;
	}
	public void setHsnsacno(String hsnsacno) {
		this.hsnsacno = hsnsacno;
	}
	public double getSgst() {
		return sgst;
	}
	public void setSgst(double sgst) {
		this.sgst = sgst;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	
	
	

}
