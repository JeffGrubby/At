package com.ylmall.at.tobject;

import java.util.ArrayList;
import java.util.Date;

import com.ylmall.at.model.SkuNeed;

/**
 * @author  作者 E-mail: 
 * @date 创建时间：2016年8月11日 下午4:31:23
 * @version 1.0
 * @parameter 
 * @since 
 * @return 
 */
public class Order {
	private String orderId;
	private String orderState;
	private ArrayList<SkuNeed> originOrderList;//sku,amount
	private Date time;
	private double money;
	private String city;
	private String shopCode;


	public Order() {
		// TODO Auto-generated constructor stub
		originOrderList = new ArrayList<SkuNeed>();
	}
	
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public ArrayList<SkuNeed> getOriginOrderList() {
		return originOrderList;
	}

	public void setOriginOrderList(ArrayList<SkuNeed> originOrderList) {
		this.originOrderList = originOrderList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



}
