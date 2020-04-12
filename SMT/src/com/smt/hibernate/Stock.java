package com.smt.hibernate;


import java.util.Date;

public class Stock {

	public long PkStockId;
	public String itemName;
	public String catName;
	private long quantity;
	private Date UpdateDate;
	private String date;
	
	
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}


	public Stock(long pkStockId, String itemName, String catName,
			long quantity, Date updateDate) {
		super();
		PkStockId = pkStockId;
		this.itemName = itemName;
		this.catName = catName;
		this.quantity = quantity;
		UpdateDate = updateDate;
	}


	public long getPkStockId() {
		return PkStockId;
	}


	public void setPkStockId(long pkStockId) {
		PkStockId = pkStockId;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getCatName() {
		System.out.println("in bean file"+catName);
		return catName;
		
	}


	public void setCatName(String catName) {
		System.out.println(catName);
		this.catName = catName;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public Date getUpdateDate() {
		return UpdateDate;
	}


	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	
	
	
	
}
