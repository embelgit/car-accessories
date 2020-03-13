package com.smt.hibernate;

import java.util.Date;

public class ProductRegister {

	private Long pkProductId;
    private Double vat;
    private Long fkCategoryId; 
    private String itemName;
    private Date isInsertDate;
    private Category category;
    private String modelName;
    private String hsnsacno;
    private String categoryName;
    
    
    
	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public ProductRegister(Long pkProductId, Double vat, Long fkCategoryId, String itemName, Date isInsertDate, Category category, String modelName, String hsnsacno) {
		super();
		this.pkProductId = pkProductId;
		this.vat = vat;
		this.fkCategoryId = fkCategoryId;
		this.itemName = itemName;
		this.isInsertDate = isInsertDate;
		this.category = category;
		this.modelName = modelName;
		this.hsnsacno = hsnsacno;
	}


	public ProductRegister() {
		super();
		// TODO Auto-generated constructor stub
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
    
    
	
	
}
