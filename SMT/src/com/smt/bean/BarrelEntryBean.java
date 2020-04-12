package com.smt.bean;

import java.util.Date;

import com.smt.hibernate.Category;

public class BarrelEntryBean {
	
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
    private double salePrice;
	private double discount;
	private long quantity;
	private double buyPrice;
	private Long pkBillId;
	private String billNo;
	private Long barcodeNo;
	
    
    
	public Long getPkBillId() {
		return pkBillId;
	}
	public void setPkBillId(Long pkBillId) {
		this.pkBillId = pkBillId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Long getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(Long barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
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
