package com.ylmall.at.service;

import java.util.List;

import com.ylmall.at.tobject.TargetShop;

public interface MatrixAlgorithmService {
	

	/**
	 * @param matrix
	 * @param ReposRow
	 * @param ReposColumn
	 * @return
	 * TODO 筛选矩阵
	 */
	public List<TargetShop> selectShop(int[][] matrix,int[] ReposRow,int[] ReposColumn);
	
	/**
	 * 
	 * @param ValidatedTargetShop
	 * @param missedSku
	 * @return
	 * TODO 重新筛选前对矩阵置零,修改忽略数组
	 */
	public  boolean modify(List<TargetShop> ValidatedTargetShop,List<String> missedSku);
	
	
}
