package com.smt.bean;

public class TallyPurchaseReport {
	
	private String itemName;
	private String catName;
	private Long quantity;
	private Long barcodeNo;
	private Double total;
	private Double buyPrice;
	private String hsnsacno;
	
	private String baseunit;
	private String alias;
	private String description;
	private String hsnsacdes;
	private String vatapplicable;
	private String gstapplicable;
	private String typesofsupply;
	private String gstapplicabledate;
	private String taxability;
	private String rateofcess;
	
	private Double igst;
	private Double sgst;
	private Double cgst;
	private Double transportExpenses;
	private String billNo;
	private String vchType;
	private String purchaseLedger;
	private String godown;
	private String partyType;
	private String batch;
	private String unit;
	private String additionalLedger;
	private String narration;
	private String gstLedger;
	private String date;
	private Double taxAmt;
	private Double grossAmt;
	
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
	public Long getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(Long barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getHsnsacno() {
		return hsnsacno;
	}
	public void setHsnsacno(String hsnsacno) {
		this.hsnsacno = hsnsacno;
	}
	public String getBaseunit() {
		return baseunit;
	}
	public void setBaseunit(String baseunit) {
		this.baseunit = baseunit;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHsnsacdes() {
		return hsnsacdes;
	}
	public void setHsnsacdes(String hsnsacdes) {
		this.hsnsacdes = hsnsacdes;
	}
	public String getVatapplicable() {
		return vatapplicable;
	}
	public void setVatapplicable(String vatapplicable) {
		this.vatapplicable = vatapplicable;
	}
	public String getGstapplicable() {
		return gstapplicable;
	}
	public void setGstapplicable(String gstapplicable) {
		this.gstapplicable = gstapplicable;
	}
	public String getTypesofsupply() {
		return typesofsupply;
	}
	public void setTypesofsupply(String typesofsupply) {
		this.typesofsupply = typesofsupply;
	}
	public String getGstapplicabledate() {
		return gstapplicabledate;
	}
	public void setGstapplicabledate(String gstapplicabledate) {
		this.gstapplicabledate = gstapplicabledate;
	}
	public String getTaxability() {
		return taxability;
	}
	public void setTaxability(String taxability) {
		this.taxability = taxability;
	}
	public String getRateofcess() {
		return rateofcess;
	}
	public void setRateofcess(String rateofcess) {
		this.rateofcess = rateofcess;
	}
	public Double getIgst() {
		return igst;
	}
	public void setIgst(Double igst) {
		this.igst = igst;
	}
	public Double getSgst() {
		return sgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	public Double getCgst() {
		return cgst;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	public Double getTransportExpenses() {
		return transportExpenses;
	}
	public void setTransportExpenses(Double transportExpenses) {
		this.transportExpenses = transportExpenses;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getVchType() {
		return vchType;
	}
	public void setVchType(String vchType) {
		this.vchType = vchType;
	}
	public String getPurchaseLedger() {
		return purchaseLedger;
	}
	public void setPurchaseLedger(String purchaseLedger) {
		this.purchaseLedger = purchaseLedger;
	}
	public String getGodown() {
		return godown;
	}
	public void setGodown(String godown) {
		this.godown = godown;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getAdditionalLedger() {
		return additionalLedger;
	}
	public void setAdditionalLedger(String additionalLedger) {
		this.additionalLedger = additionalLedger;
	}
	public String getGstLedger() {
		return gstLedger;
	}
	public void setGstLedger(String gstLedger) {
		this.gstLedger = gstLedger;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public Double getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(Double taxAmt) {
		this.taxAmt = taxAmt;
	}
	public Double getGrossAmt() {
		return grossAmt;
	}
	public void setGrossAmt(Double grossAmt) {
		this.grossAmt = grossAmt;
	}
	
	
	
	/*private Double salePrice;
	
	private String billNo;
	private String contactPerson;
	private Double vat;
	private String date;
	private Double expence;
	private Double grossTotal;
	
	private String supplierName;
	private String ondate;
	private Long oringnalQuantity;
	private Double igst;
	private Double taxAmount;
	
	private Double extraDiscount;
	private String paymentDone;
	private Double txPerexpence;
	private Double finalExpenses;*/

}
