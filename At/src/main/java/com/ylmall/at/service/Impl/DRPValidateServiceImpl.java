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
	 * �������:����ɸѡ���
	 * ѭ��list
	 * 1.����DRP�ӿ�,�����ŵ�,Sku����at������Ƚ�����
	 * 2.��DRPС��at�����at�����򲻸���
	 * 3.��ѯ���������ж�Ӧsku,orderId���������Ƚϴ�С
	 * 4.��DRPС�ڶ���������orderIdΪnull����������orderId
	 * �������ϸ��sku�б�
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