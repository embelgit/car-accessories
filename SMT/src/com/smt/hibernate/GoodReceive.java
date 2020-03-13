package com.smt.hibernate;

import java.util.Date;

public class GoodReceive {
	
	private long txId;
	private Long PkGoodRecId;
	private String itemName;
	private String catName;
	private Long quantity;
	private Double buyPrice;
	private Double salePrice;
	private Double total;
	private String billNo;
	private String contactPerson;
	private Double vat;
	private Date date;
	private Double expence;
	private Double grossTotal;
	private Long barcodeNo;
	private Long supplierName;
	private String ondate;
	private Long oringnalQuantity;
	private Double igst;
	private Double taxAmount;
	private String hsnsacno;
	private Double extraDiscount;
	private String paymentDone;
	private Double txPerexpence;
	private Double finalExpenses;
	private Double discount;
	
	
	
	public long getTxId() {
		return txId;
	}


	public void setTxId(long txId) {
		this.txId = txId;
	}


	public GoodReceive(Long pkGoodRecId, String itemName, String catName, Long quantity, Double buyPrice, Double salePrice,Double discount ,Double total, String billNo, String contactPerson, Double vat, Date date, Double expence, Double grossTotal, Long barcodeNo, Long supplierName, String ondate, Long oringnalQuantity, Double igst, Double taxAmount, String hsnsacno, Double extraDiscount, String paymentDone, Double txPerexpence, Double finalExpenses) {
		super();
		PkGoodRecId = pkGoodRecId;
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
		this.igst = igst;
		this.taxAmount = taxAmount;
		this.hsnsacno = hsnsacno;
		this.extraDiscount = extraDiscount;
		this.paymentDone = paymentDone;
		this.txPerexpence = txPerexpence;
		this.finalExpenses = finalExpenses;
		this.discount = discount;
	}


	public GoodReceive() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Double getDiscount() {
		return discount;
	}


	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public Long getPkGoodRecId() {
		return PkGoodRecId;
	}


	public void setPkGoodRecId(Long pkGoodRecId) {
		PkGoodRecId = pkGoodRecId;
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


	public String getBillNo() {
		return billNo;
	}


	public void setBillNo(String billNo) {
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


	public Long getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(Long supplierName) {
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


	public Double getIgst() {
		return igst;
	}


	public void setIgst(Double igst) {
		this.igst = igst;
	}


	public Double getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}


	public String getHsnsacno() {
		return hsnsacno;
	}


	public void setHsnsacno(String hsnsacno) {
		this.hsnsacno = hsnsacno;
	}


	public Double getExtraDiscount() {
		return extraDiscount;
	}


	public void setExtraDiscount(Double extraDiscount) {
		this.extraDiscount = extraDiscount;
	}


	public String getPaymentDone() {
		return paymentDone;
	}


	public void setPaymentDone(String paymentDone) {
		this.paymentDone = paymentDone;
	}


	public Double getTxPerexpence() {
		return txPerexpence;
	}


	public void setTxPerexpence(Double txPerexpence) {
		this.txPerexpence = txPerexpence;
	}


	public Double getFinalExpenses() {
		return finalExpenses;
	}


	public void setFinalExpenses(Double finalExpenses) {
		this.finalExpenses = finalExpenses;
	}
	
	
	
	
	
	
}	