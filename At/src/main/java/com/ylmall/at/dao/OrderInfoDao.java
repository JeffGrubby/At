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
 * 2016����10:07:13
 */
public interface OrderInfoDao {
	
	/** ������������붩����ϸ��*/
	int insertOrderInfo(Order order);
	
	/** ����ֽ��������붩����ϸ��*/
	int insertResult(ResultShop resultShop);
	
	/** ��ѯ������ϸ���д���Ķ����ŵ�shopCode*/
	List<String> selectShopCode(String orderId);
	
	/** ��ѯ����*/
	List<OrderInfo> selectOrderInfo();
}
