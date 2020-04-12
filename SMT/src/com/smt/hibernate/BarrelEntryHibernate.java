package com.smt.hibernate;

import java.util.Date;

public class BarrelEntryHibernate {
	
	private Long pkProductId;
    private Double vat;
    private Long fkCategoryId; 
    private String itemName;
    private Date isInsertDate;
    private Category category;
    private String modelName;
    private String hsnsacno;
    private String categoryName;
    private Double NumberofBarrel;
    private Double oilperlitre;
    private Double TotalLitre;
    
    private long txId;
	private Long PkGoodRecId;
	private String catName;
	private Long quantity;
	private Double buyPrice;
	private Double salePrice;
	private Double total;
	private String billNo;
	private String contactPerson;
	private Date date;
	private Double expence;
	private Double grossTotal;
	private Long barcodeNo;
	private Long supplierName;
	private String ondate;
	private Long oringnalQuantity;
	private Double igst;
	private Double taxAmount;
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
	public Long getPkGoodRecId() {
		return PkGoodRecId;
	}
	public void setPkGoodRecId(Long pkGoodRecId) {
		PkGoodRecId = pkGoodRecId;
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
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Long getPkProductId() {
		return pkProductId;
	}
	public void setPkProductId(Long pkProductId) {
		this.pkProductId = pkProductId;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Long getFkCategoryId() {
		return fkCategoryId;
	}
	public void setFkCategoryId(Long fkCategoryId) {
		this.fkCategoryId = fkCategoryId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Date getIsInsertDate() {
		return isInsertDate;
	}
	public void setIsInsertDate(Date isInsertDate) {
		this.isInsertDate = isInsertDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getHsnsacno() {
		return hsnsacno;
	}
	public void setHsnsacno(String hsnsacno) {
		this.hsnsacno = hsnsacno;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Double getNumberofBarrel() {
		return NumberofBarrel;
	}
	public void setNumberofBarrel(Double numberofBarrel) {
		NumberofBarrel = numberofBarrel;
	}
	public Double getOilperlitre() {
		return oilperlitre;
	}
	public void setOilperlitre(Double oilperlitre) {
		this.oilperlitre = oilperlitre;
	}
	public Double getTotalLitre() {
		return TotalLitre;
	}
	public void setTotalLitre(Double totalLitre) {
		TotalLitre = totalLitre;
	}
    
    

}
