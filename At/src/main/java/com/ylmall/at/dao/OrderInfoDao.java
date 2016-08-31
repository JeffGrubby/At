/**
 * 
 */
package com.ylmall.at.dao;

import java.util.List;

import com.ylmall.at.model.OrderInfo;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.ResultShop;

/**
 * At.com.ylmall.at.dao
 * @author JeffGrubb
 * @version 1.0
 * 2016上午10:07:13
 */
public interface OrderInfoDao {
	
	/** 将订单对象插入订单明细表*/
	int insertOrderInfo(Order order);
	
	/** 将拆分结果对象插入订单明细表*/
	int insertResult(ResultShop resultShop);
	
	/** 查询订单明细表中传入的订单号的shopCode*/
	List<String> selectShopCode(String orderId);
	
	/** 查询订单*/
	List<OrderInfo> selectOrderInfo();
}
