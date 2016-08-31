/**
 * 
 */
package com.ylmall.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ylmall.at.model.Stock;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.ResultShop;
import com.ylmall.at.tobject.SelectedShop;

/**
 * At.com.ylmall.at.dao
 * @author JeffGrubb
 * @version 1.0
 * 2016����10:08:23
 */
public interface StockDao {
	/**������ */
	int insertStock(List<Stock> stock);
	/**���¿���(DRP����)*/
		int updateStock(Stock stock);
	/**���¿���(���ս��)*/
		int updateStockByResult(ResultShop resultShop);
	/**ȫ��ɸѡ(�״β�)*/
		List<SelectedShop> selectSkuShop(Order order);
	/**�ų��ض��ŵ���ɸѡ(���²�)*/
		List<SelectedShop> reselectSkuShop(Map<String,Object> map);
	/**��ѯ�����ж�Ӧ�ŵ�,sku������*/
		int selectAmount(@Param("shopCode")String shopCode,@Param("sku")String sku);

	
}
