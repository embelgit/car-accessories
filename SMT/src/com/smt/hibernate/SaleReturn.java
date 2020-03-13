package com.smt.hibernate;

import java.util.Date;

public class SaleReturn {
	
	private Long pkBillId;
	private Long catId;
	private String itemName;
	private Long quantity;
	private Double salePrice;
	private Double grossamt;
	private Date current_date;
	private Double totalAmt;
	private Long billNo;
	private String categoryName;
	
	private Long barcodeNo;
	private Long contactNo;
	private Double discount;
	
	private String carNo;
	private String Date;
	private Long editQuantity;
	private Long afterQuantity;
	
	
	
	
	
	
	public SaleReturn(Long pkBillId, Long catId, String itemName,
			Long quantity, Double salePrice, Double grossamt,
			java.util.Date current_date, Double totalAmt, Long billNo,
			String categoryName, Long barcodeNo, Long contactNo,
			Double discount, String carNo, String date, Long editQuantity) {
		super();
		this.pkBillId = pkBillId;
		this.catId = catId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.salePrice = salePrice;
		this.grossamt = grossamt;
		this.current_date = current_date;
		this.totalAmt = totalAmt;
		this.billNo = billNo;
		this.categoryName = categoryName;
		this.barcodeNo = barcodeNo;
		this.contactNo = contactNo;
		this.discount = discount;
		this.carNo = carNo;
		Date = date;
		this.editQuantity = editQuantity;
	}
	
	
	
	public SaleReturn() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getPkBillId() {
		return pkBillId;
	}
	public void setPkBillId(Long pkBillId) {
		this.pkBillId = pkBillId;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getGrossamt() {
		return grossamt;
	}
	public void setGrossamt(Double grossamt) {
		this.grossamt = grossamt;
	}
	public Date getCurrent_date() {
		return current_date;
	}
	public void setCurrent_date(Date current_date) {
		this.current_date = current_date;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Long getBillNo() {
		return billNo;
	}
	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(Long barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Long getEditQuantity() {
		return editQuantity;
	}
	public void setEditQuantity(Long editQuantity) {
		this.editQuantity = editQuantity;
	}



	public Long getAfterQuantity() {
		return afterQuantity;
	}



	public void setAfterQuantity(Long afterQuantity) {
		this.afterQuantity = afterQuantity;
	}
	
	

}
