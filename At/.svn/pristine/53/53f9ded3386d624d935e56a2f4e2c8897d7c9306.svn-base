/**
 * 
 */
package com.ylmall.at.util;

import java.util.List;

import com.ylmall.at.model.SkuNeed;
import com.ylmall.at.service.Impl.MatrixServiceImpl;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.TargetShop;

/**
 * At.com.ylmall.at.util
 * @author JeffGrubb
 * @version 1.0
 * 2016上午8:40:30
 */
public class ResultUtil {

	/**
	 * 
	 * @param 订单对象
	 * @param 子订单列表
	 * @param 矩阵初始化服务对象
	 * TODO 将sql未查询到的sku补充到"NoShopMatched"子订单中
	 */
	public static void addSqlMissedSku(Order order,List<TargetShop> targetShopList,MatrixServiceImpl ms){
			
			int k=0;
			int t = 0;
			for(int i = 0;i<order.getOriginOrderList().size();i++){
				for(int j = 0;j<ms.getSkuArray().length;j++)
				{
					if(!order.getOriginOrderList().get(i).getSku().equals(ms.getSkuArray()[j])) 
						k++;
				}
				
			if(k==ms.getSkuArray().length)
			 {				 
			  for(;t<targetShopList.size();t++)
				{
				   if(targetShopList.get(t).getShopCode().equals("NoShopMatched"))
						{ 
							  targetShopList.get(t).getInfoList().add(new SkuNeed(order.getOriginOrderList().get(i).getSku(),
									  order.getOriginOrderList().get(i).getAmount()));		  
							  break;
						 }	 
				}
			  if(t==targetShopList.size())
					targetShopList.add(new TargetShop("NoShopMatched",new SkuNeed(order.getOriginOrderList().get(i).getSku(),
							  order.getOriginOrderList().get(i).getAmount())));	
				
				
				}
			k=0;
			t=0;
		}
			
			
	}
		

	
	/**
	 * 
	 * @param 子订单列表
	 * @param 矩阵初始化服务对象
	 * @param 行忽略数组
	 * @param 二维矩阵
	 * TODO 将算法匹配未中的sku填写到"NoShopMatched"子订单中
	 */
	public static void addMissedSku(List<TargetShop> targetShopList,MatrixServiceImpl ma,int[] rowIgnore,int[][] matrix){
		int k =0;
		TargetShop temp = new TargetShop();
		SkuNeed skuNeed;
		for(int i=0;i<ma.getSkuArray().length;i++){
			for(int j=0;j<ma.getShopArray().length;j++)
			{
				if(matrix[i][j]==0) k++;
				
			}
			if(k==ma.getShopArray().length){
				System.out.println("hello there!");
				rowIgnore[i]=-1;
				temp.setShopCode("NoShopMatched");		
				skuNeed = new SkuNeed();
				skuNeed.setSku(ma.getSkuArray()[i].toString());
				skuNeed.setAmount(ma.getAmountArray()[i]);
				temp.getInfoList().add(skuNeed);				
				targetShopList.add(temp);
			}
			k=0;
		}
		
	}
	
}
