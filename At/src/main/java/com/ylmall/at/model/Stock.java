package com.ylmall.at.model;
/**
 * @description TODO
 * @author Administrator
 * @date 2016年8月11日下午2:40:32
 * @version
 */
public class Stock {

	/**
	 * 库存信息表
	 */
	
	private String sku;
	private String shopCode;
	private int amount;
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	public String getShopCode() {
		return shopCode;
	}


	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}


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


	

}
