package com.ylmall.at.tobject;

import java.util.ArrayList;
import java.util.Date;

import com.ylmall.at.model.SkuNeed;

/**
 * @author  作者 E-mail: 
 * @date 创建时间：2016年8月11日 上午11:09:53
 * @version 1.0
 * @parameter 
 * @since 
 * @return 
 */
public class TargetShop {

	/**
	 * 命中算法筛选后选中的门店
	 */
	
	private String orderId;
	private String shopCode;
	private ArrayList<SkuNeed> infoList;//sku,amount
	private String state;
	private Date time;

	public Date getTime() {
		return time;
	}



	public void setTime(Date time) {
		this.time = time;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public TargetShop() {
		// TODO Auto-generated constructor stub
		infoList = new ArrayList<SkuNeed>();
	}
	public TargetShop(String shopCode,SkuNeed s) {
		infoList = new ArrayList<SkuNeed>();
		this.shopCode = shopCode;
		this.infoList.add(s);
	}

	
	public String getShopCode() {
		return shopCode;
	}
	
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}



	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public ArrayList<SkuNeed> getInfoList() {
		return infoList;
	}



	public void setInfoList(ArrayList<SkuNeed> infoList) {
		this.infoList = infoList;
	}












}
