package com.ylmall.at.service;

import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.ResultShop;

/**
 * @author  作者 E-mail: 
 * @date 创建时间：2016年8月11日 上午11:13:48
 * @version 1.0
 * @parameter 
 * @since 
 * @return 
 */


public interface ShopSelectService {
	
	/**
	 *  传入判空
	 *  调用MatrixAlgorithmService,DRPValidateService
	 *  返回ResultShop
	 */
	ResultShop resultShop(Order order);
	
	
}
