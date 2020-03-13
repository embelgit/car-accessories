package com.smt.hibernate;

import java.util.Date;

public class CustomerBill {
	
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
	private Long pkItemId;
	private Long barcodeNo;
	private Long contactNo;
	private Double discount;
	private Double laberCharges;
	private String ownerName;
	private String carNo;
	private Double totalperItem;
	private String hsnSacNo;
	private Double vat;
	private Double igst;
	private Double taxAmount;
	private Double discountGrid;
	private Double discountAmt;
	////service///
	
	private Double serviceTotalAmt;
	private Double servicetotalPerItem;
	private String service_item;
	private String service_hsn;
	private Long service_quantity;
	private Double service_saleprice;
	private Double service_disc_grid;
	private Double service_discAmt;
	private Double service_gst;
	private Double service_igst;
	private Double service_taxAm;
	
	
	
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
	public Double getServiceTotalAmt() {
		return serviceTotalAmt;
	}


	public void setServiceTotalAmt(Double serviceTotalAmt) {
		this.serviceTotalAmt = serviceTotalAmt;
	}


	public Double getServicetotalPerItem() {
		return servicetotalPerItem;
	}


	public void setServicetotalPerItem(Double servicetotalPerItem) {
		this.servicetotalPerItem = servicetotalPerItem;
	}


	public String getService_item() {
		return service_item;
	}


	public void setService_item(String service_item) {
		this.service_item = service_item;
	}


	public String getService_hsn() {
		return service_hsn;
	}


	public void setService_hsn(String service_hsn) {
		this.service_hsn = service_hsn;
	}


	public Long getService_quantity() {
		return service_quantity;
	}


	public void setService_quantity(Long service_quantity) {
		this.service_quantity = service_quantity;
	}


	public Double getService_saleprice() {
		return service_saleprice;
	}


	public void setService_saleprice(Double service_saleprice) {
		this.service_saleprice = service_saleprice;
	}


	public Double getService_disc_grid() {
		return service_disc_grid;
	}


	public void setService_disc_grid(Double service_disc_grid) {
		this.service_disc_grid = service_disc_grid;
	}


	public Double getService_discAmt() {
		return service_discAmt;
	}


	public void setService_discAmt(Double service_discAmt) {
		this.service_discAmt = service_discAmt;
	}


	public Double getService_gst() {
		return service_gst;
	}


	public void setService_gst(Double service_gst) {
		this.service_gst = service_gst;
	}


	public Double getService_igst() {
		return service_igst;
	}


	public void setService_igst(Double service_igst) {
		this.service_igst = service_igst;
	}


	public Double getService_taxAm() {
		return service_taxAm;
	}


	public void setService_taxAm(Double service_taxAm) {
		this.service_taxAm = service_taxAm;
	}


	public CustomerBill(Long pkBillId, Long catId, String itemName, Long quantity, Double salePrice, Double grossamt, Date current_date, Double totalAmt, Long billNo, String categoryName, Long pkItemId, Long barcodeNo, Long contactNo, Double discount, Double laberCharges, String ownerName, String carNo, Double totalperItem, String hsnSacNo, Double vat, Double igst, Double taxAmount) {
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
		this.pkItemId = pkItemId;
		this.barcodeNo = barcodeNo;
		this.contactNo = contactNo;
		this.discount = discount;
		this.laberCharges = laberCharges;
		this.ownerName = ownerName;
		this.carNo = carNo;
		this.totalperItem = totalperItem;
		this.hsnSacNo = hsnSacNo;
		this.vat = vat;
		this.igst = igst;
		this.taxAmount = taxAmount;
	}


	public CustomerBill() {
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


	public Double getLaberCharges() {
		return laberCharges;
	}


	public void setLaberCharges(Double laberCharges) {
		this.laberCharges = laberCharges;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getCarNo() {
		return carNo;
	}


	public void setCarNo(String carNo) {
		this.carNo = carNo;
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
	
	
}
