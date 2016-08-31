package com.ylmall.at.model;
/**
 * @description TODO
 * @author Administrator
 * @date 2016年8月11日下午2:40:24
 * @version
 */
public class Shop {

	/**
	 * 门店信息表
	 */
	private String shopCode;
	private String city;
	private int level;
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Shop() {
		// TODO Auto-generated constructor stub
	}

	public String getShopCode() {
		return shopCode;
	}


	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}


}
