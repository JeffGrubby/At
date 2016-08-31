/**
 * 
 */
package com.ylmall.at.dao;

import java.util.List;

import com.ylmall.at.model.Shop;
import com.ylmall.at.tobject.Order;

/**
 * At.com.ylmall.at.dao
 * @author JeffGrubb
 * @version 1.0
 * 2016上午10:07:59
 */
public interface ShopDao {
	// TODO 查询订单地址的全部门店
	List<String> selecShop(Order order);
    // TODO 插入门店信息
	int insertShop(List<Shop> shop);
}
