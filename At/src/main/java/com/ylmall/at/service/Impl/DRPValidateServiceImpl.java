package com.ylmall.at.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ylmall.at.dao.OrderInfoDao;
import com.ylmall.at.dao.StockDao;
import com.ylmall.at.service.DRPValidateService;
import com.ylmall.at.tobject.TargetShop;

@Service("DRPValidateService")

public class DRPValidateServiceImpl implements DRPValidateService{
	private Logger logger = LogManager.getLogger(DRPValidateServiceImpl.class.getName());
	private StockDao stockDao;
	private OrderInfoDao orderInfoDao;
	
	/**
	 * 传入参数:矩阵筛选结果
	 * 循环list
	 * 1.调用DRP接口,传入门店,Sku并与at缓存表比较数量
	 * 2.若DRP小于at则更新at，否则不更新
	 * 3.查询订单对象中对应sku,orderId的数量，比较大小
	 * 4.若DRP小于订单数量则orderId为null，否则生成orderId
	 * 传出不合格的sku列表
	 */
	public List<String> Validate(List<TargetShop> targetShopList) {
		logger.info("Validating results");
		// TODO Auto-generated method stub
		List<String> missedSku = new ArrayList<String>();
		
		
		
		logger.info("Validating done");
		return missedSku;
	}
	
	
	
	public DRPValidateServiceImpl() {
		// TODO Auto-generated constructor stub
	}
}
