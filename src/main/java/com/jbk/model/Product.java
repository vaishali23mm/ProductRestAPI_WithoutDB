package com.jbk.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Product {

	private long productId;
	
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "Invalid Product Name")
	private String productName;
	
	@Min(value = 1,  message = "Invalid Product QTY")
	private int productQty;
	
	@Min(value = 1,  message = "Invalid Product Price")
	private double productPrice;
	
	 @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "MFG Date format must be yyyy-MM-dd")
	private String mfgDate;
	 @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "EXP Date format must be yyyy-MM-dd")
	private String expDate;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	public Product(long productId, String productName, int productQty, double productPrice, String mfgDate,
			String expDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.mfgDate = mfgDate;
		this.expDate = expDate;
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getProductQty() {
		return productQty;
	}


	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public String getMfgDate() {
		return mfgDate;
	}


	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}


	public String getExpDate() {
		return expDate;
	}


	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQty=" + productQty
				+ ", productPrice=" + productPrice + ", mfgDate=" + mfgDate + ", expDate=" + expDate + "]";
	}
	
	
	

}
