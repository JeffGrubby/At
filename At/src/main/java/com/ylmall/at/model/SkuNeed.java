/**
 * 
 */
package com.ylmall.at.model;

/**
 * At.com.ylmall.at.model
 * @author JeffGrubb
 * @version 1.0
 * 2016обнГ3:07:13
 */
public class SkuNeed {

	/**
	 * 
	 */
	
	private String sku;
	private int amount;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	

	public SkuNeed(String sku,int amount){
		this.sku = sku;
		this.amount = amount;
		
	}
	public SkuNeed() {
		// TODO Auto-generated constructor stub
	}

}
