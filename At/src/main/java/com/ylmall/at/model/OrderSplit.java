package com.ylmall.at.model;
/**
 * @description TODO
 * @author Administrator
 * @date 2016年8月11日下午2:40:12
 * @version
 */
public class OrderSplit {

	/**
	 * 订单拆分表
	 */
	private String originOrderId;
	private String childOrderId;
	
	
	public OrderSplit() {
		// TODO Auto-generated constructor stub
	}
	
	public String getOriginOrderId() {
		return originOrderId;
	}
	
	public void setOriginOrderId(String originOrderId) {
		this.originOrderId = originOrderId;
	}
	
	public String getChildOrderId() {
		return childOrderId;
	}
	
	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}

	
}
