package com.smt.hibernate;

import java.util.Date;

public class PurchaseReturn {
	
	private Long PkPurchaseReturnId;
	private String itemName;
	private String catName;
	private Long quantity;
	private Double buyPrice;
	private Double salePrice;
	private Double total;
	private Long billNo;
	private String contactPerson;
	private Double vat;
	private Date date;
	private Double expence;
	private Double grossTotal;
	private Long barcodeNo;
	private String supplierName;
	private String ondate;
	private Long oringnalQuantity;
	
	
	
	public PurchaseReturn(Long PkPurchaseReturnId, String itemName, String catName,
			Long quantity, Double buyPrice, Double salePrice, Double total,
			Long billNo, String contactPerson, Double vat, Date date,
			Double expence, Double grossTotal, Long barcodeNo,
			String supplierName, String ondate, Long oringnalQuantity) {
		super();
		PkPurchaseReturnId = PkPurchaseReturnId;
		this.itemName = itemName;
		this.catName = catName;
		this.quantity = quantity;
		this.buyPrice = buyPrice;
		this.salePrice = salePrice;
		this.total = total;
		this.billNo = billNo;
		this.contactPerson = contactPerson;
		this.vat = vat;
		this.date = date;
		this.expence = expence;
		this.grossTotal = grossTotal;
		this.barcodeNo = barcodeNo;
		this.supplierName = supplierName;
		this.ondate = ondate;
		this.oringnalQuantity = oringnalQuantity;
	}



	public PurchaseReturn() {
		super();
		// TODO Auto-generated constructor stub
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



	public Long getQuantity() {
		return quantity;
	}



	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}



	public Double getBuyPrice() {
		return buyPrice;
	}



	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}



	public Double getSalePrice() {
		return salePrice;
	}



	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}



	public Double getTotal() {
		return total;
	}



	public void setTotal(Double total) {
		this.total = total;
	}



	public Long getBillNo() {
		return billNo;
	}



	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}



	public String getContactPerson() {
		return contactPerson;
	}



	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}



	public Double getVat() {
		return vat;
	}



	public void setVat(Double vat) {
		this.vat = vat;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Double getExpence() {
		return expence;
	}



	public void setExpence(Double expence) {
		this.expence = expence;
	}



	public Double getGrossTotal() {
		return grossTotal;
	}



	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
	}



	public Long getBarcodeNo() {
		return barcodeNo;
	}



	public void setBarcodeNo(Long barcodeNo) {
		this.barcodeNo = barcodeNo;
	}



	public String getSupplierName() {
		return supplierName;
	}



	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}



	public String getOndate() {
		return ondate;
	}



	public void setOndate(String ondate) {
		this.ondate = ondate;
	}



	public Long getOringnalQuantity() {
		return oringnalQuantity;
	}



	public void setOringnalQuantity(Long oringnalQuantity) {
		this.oringnalQuantity = oringnalQuantity;
	}



	public Long getPkPurchaseReturnId() {
		return PkPurchaseReturnId;
	}



	public void setPkPurchaseReturnId(Long pkPurchaseReturnId) {
		PkPurchaseReturnId = pkPurchaseReturnId;
	}
	
	
	
	

}
