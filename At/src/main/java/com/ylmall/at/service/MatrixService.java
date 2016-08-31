/**
 * 
 */
package com.ylmall.at.service;

import java.util.List;

import com.ylmall.at.tobject.SelectedShop;



/**
 * At.com.ylmall.at.service
 * @author JeffGrubb
 * @version 1.0
 * 2016上午11:52:06
 */
public interface MatrixService {

	/**
	 * @param shopList
	 * TODO 初始化并输出矩阵
	 */
	public void initMatrix(List<SelectedShop> shopList);
	
}
