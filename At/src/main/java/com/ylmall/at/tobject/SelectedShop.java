package com.ylmall.at.tobject;



/**
 * @author  作者 E-mail: 
 * @date 创建时间：2016年8月11日 上午10:24:32
 * @version 1.0
 * @parameter 
 * @since 
 * @return 
 */
public class SelectedShop {

	/**
	 * sql筛选出的可以满足发货单个或多个sku的门店对象（不包括线上5号仓库）
	 */
	
	private String sku;
	private String shopCode;
	
	
	public SelectedShop() {
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
	


	




}
