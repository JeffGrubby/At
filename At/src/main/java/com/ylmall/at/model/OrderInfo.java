package com.ylmall.at.model;
/**
 * 
 * @description TODO
 * @author Administrator
 * @date 2016年8月11日下午2:39:58
 * @version
 */

public class OrderInfo {

	/**
	 * 订单明细表
	 */
	private String orderId;
	private String sku;
	private int amount;
	private String shopCode;
	


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	
}
