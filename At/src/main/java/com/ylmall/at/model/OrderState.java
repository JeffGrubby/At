package com.ylmall.at.model;
/**
 * 
 * @description TODO
 * @author Administrator
 * @date 2016年8月11日下午2:40:18
 * @version
 */
public class OrderState {

	/**
	 * 订单状态
	 */
	private String orderId;
	private String orderState;
	
	public OrderState() {
		// TODO Auto-generated constructor stub
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


}
