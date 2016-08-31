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
 * 2016上午10:08:23
 */
public interface StockDao {
	/**插入库存 */
	int insertStock(List<Stock> stock);
	/**更新库存表(DRP检验)*/
		int updateStock(Stock stock);
	/**更新库存表(最终结果)*/
		int updateStockByResult(ResultShop resultShop);
	/**全体筛选(首次拆单)*/
		List<SelectedShop> selectSkuShop(Order order);
	/**排除特定门店再筛选(重新拆单)*/
		List<SelectedShop> reselectSkuShop(Map<String,Object> map);
	/**查询库存表中对应门店,sku的数量*/
		int selectAmount(@Param("shopCode")String shopCode,@Param("sku")String sku);

	
}
