package com.ylmall.at.model;
/**
 * @description TODO
 * @author Administrator
 * @date 2016��8��11������2:40:32
 * @version
 */
public class Stock {

	/**
	 * �����Ϣ��
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