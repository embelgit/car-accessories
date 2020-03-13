package com.smt.hibernate;

import java.util.Date;

public class CreditCustomerBill {

	private Long pkCreditBillId;
	private Long fkRootCustId;
	private String itemName;
	private Long quantity;
	private Double salePrice;
	private Double grossamt;
	private Date current_date;
	private Double totalAmt;
	private Long billNo;
	private String categoryName;
	private Long pkItemId;
	private Long barcodeNo;
	private Double discount;
	private Double totalperItem;
	private String hsnSacNo;
	private Double vat;
	private Double igst;
	private Double taxAmount;
	private String paymentDone;
	private Double discountGrid;
	private Double discountAmt;
	
	
	
	
	public Double getDiscountGrid() {
		return discountGrid;
	}

	public void setDiscountGrid(Double discountGrid) {
		this.discountGrid = discountGrid;
	}

	public Double getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(Double discountAmt) {
		this.discountAmt = discountAmt;
	}

	public CreditCustomerBill(Long pkCreditBillId, Long fkRootCustId, String itemName, Long quantity, Double salePrice, Double grossamt, Date current_date, Double totalAmt, Long billNo, String categoryName, Long pkItemId, Long barcodeNo, Double discount, Double totalperItem, String hsnSacNo, Double vat, Double igst, Double taxAmount, String paymentDone) {
		super();
		this.pkCreditBillId = pkCreditBillId;
		this.fkRootCustId = fkRootCustId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.salePrice = salePrice;
		this.grossamt = grossamt;
		this.current_date = current_date;
		this.totalAmt = totalAmt;
		this.billNo = billNo;
		this.categoryName = categoryName;
		this.pkItemId = pkItemId;
		this.barcodeNo = barcodeNo;
		this.discount = discount;
		this.totalperItem = totalperItem;
		this.hsnSacNo = hsnSacNo;
		this.vat = vat;
		this.igst = igst;
		this.taxAmount = taxAmount;
		this.paymentDone = paymentDone;
	}

	public CreditCustomerBill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getPkCreditBillId() {
		return pkCreditBillId;
	}

	public void setPkCreditBillId(Long pkCreditBillId) {
		this.pkCreditBillId = pkCreditBillId;
	}

	public Long getFkRootCustId() {
		return fkRootCustId;
	}

	public void setFkRootCustId(Long fkRootCustId) {
		this.fkRootCustId = fkRootCustId;
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

	public Long getPkItemId() {
		return pkItemId;
	}

	public void setPkItemId(Long pkItemId) {
		this.pkItemId = pkItemId;
	}

	public Long getBarcodeNo() {
		return barcodeNo;
	}

	public void setBarcodeNo(Long barcodeNo) {
		this.barcodeNo = barcodeNo;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotalperItem() {
		return totalperItem;
	}

	public void setTotalperItem(Double totalperItem) {
		this.totalperItem = totalperItem;
	}

	public String getHsnSacNo() {
		return hsnSacNo;
	}

	public void setHsnSacNo(String hsnSacNo) {
		this.hsnSacNo = hsnSacNo;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
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

	public String getPaymentDone() {
		return paymentDone;
	}

	public void setPaymentDone(String paymentDone) {
		this.paymentDone = paymentDone;
	}
	
   
}
