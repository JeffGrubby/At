package com.ylmall.at.model;

import java.util.Date;

/**
 * @description TODO
 * @author Administrator
 * @date 2016��8��11������2:40:43
 * @version
 */
public class StockChange {

	/**
	 * �����ϸ�仯��
	 */
	private String shopCode;
	private String sku;
	private int changes;
	private Date time;
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public StockChange() {
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

	public int getChanges() {
		return changes;
	}

	public void setChanges(int changes) {
		this.changes = changes;
	}

}