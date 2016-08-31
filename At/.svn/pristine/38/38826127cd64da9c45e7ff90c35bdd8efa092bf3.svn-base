package com.ylmall.at.service.Impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ylmall.at.dao.OrderInfoDao;
import com.ylmall.at.dao.OrderSplitDao;
import com.ylmall.at.dao.OrderStateDao;
import com.ylmall.at.dao.StockChangeDao;
import com.ylmall.at.dao.StockDao;
import com.ylmall.at.service.DRPValidateService;
import com.ylmall.at.service.MatrixAlgorithmService;
import com.ylmall.at.service.ShopSelectService;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.ResultShop;
import com.ylmall.at.tobject.SelectedShop;
import com.ylmall.at.tobject.TargetShop;
import com.ylmall.at.util.ExcelUtil;
import com.ylmall.at.util.GenerateIdUtil;
import com.ylmall.at.util.ResultUtil;
import com.ylmall.at.util.StringUtil;

/**
 * 
 * At.com.ylmall.at.service.Impl
 * @author JeffGrubb
 * @version 1.0
 * 2016下午3:42:29
 */


@Service("shopSelectService")
public class ShopSelectServiceImpl implements ShopSelectService{
	private Logger logger = LogManager.getLogger(ShopSelectServiceImpl.class.getName());
	private ResultShop resultShop;
	private OrderInfoDao orderInfoDao;
	private OrderStateDao orderStateDao;
	private OrderSplitDao orderSplitDao;
	private StockDao stockDao;
	private StockChangeDao stockChangeDao;
	private MatrixAlgorithmService ma;
	private DRPValidateService drp;
	
		
	/**实现命中算法接口 */
	public ResultShop resultShop(Order order) {
		logger.info("Generating resultShop");
		resultShop = new ResultShop();
		List<SelectedShop>	selectedShopList;
		List<TargetShop> targetShopList;
		List<String> missedSku;
		MatrixServiceImpl ms;
		
		// TODO 对订单对象判空
		if(null==order||0==order.getOriginOrderList().size()){
			// TODO 输出日志
			logger.info("Order is null");
			return null;
		}
			
		// TODO 查询订单明细表中此订单号对应shopCode
		
		List<String> shopCode=orderInfoDao.selectShopCode(order.getOrderId());
		
		if(0==shopCode.size()){
			// TODO sql查询符合订单条件的全部门店集合(首次拆单)
			selectedShopList = stockDao.selectSkuShop(order);
			// TODO 将订单对象插入订单状态，订单明细表
			orderStateDao.insertOrderState(order);
			orderInfoDao.insertOrderInfo(order);
			/*for(int i=0;i<order.getOriginOrderList().size();i++)
				orderInfoDao.insertOrderInfo(order.getOrderId(), order.getOriginOrderList().get(i).get("sku").toString(),
					 StringUtil.toInt(order.getOriginOrderList().get(i).get("amount").toString()), null);*/
		}
		else{
			// TODO 排除特定门店再筛选(重新拆单)
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("order", order.getOriginOrderList());
			map.put("shopCode", shopCode);
			selectedShopList = stockDao.reselectSkuShop(map);
			// TODO 修改订单状态表状态为"待拆分"
			order.setOrderState("running");
			orderStateDao.UpdateOrderState(order);
		}
		
		
		// TODO 对sql查询结果判空
		if(null==selectedShopList||0==selectedShopList.size()){
			// TODO 输出日志
			logger.error("error selectShop");
			return null;
		}
			
		// TODO 初始化矩阵(order是为了赋值对应sku的数量)
		ms = new MatrixServiceImpl(order);
		ms.initMatrix(selectedShopList);

		// TODO 矩阵筛选
		ma = new MatrixAlgorithmServiceImpl(ms);
		targetShopList = ma.selectShop(ms.getMatrix(), ms.getReposRow(), ms.getReposColumn());
		
		// TODO 输出初始矩阵信息（日志打印）
		logger.info(StringUtil.MatrixToStr(ms.getMatrix(), ms.getShopArray(), ms.getSkuArray()));
		
		// TODO DRP循环校验
		missedSku = drp.Validate(targetShopList);
		
		while(null!=missedSku){
			ma.modify(targetShopList, missedSku);
			// TODO 输出初始矩阵信息（日志打印）
			logger.info(StringUtil.MatrixToStr(ms.getMatrix(), ms.getShopArray(), ms.getSkuArray()));
			targetShopList = ma.selectShop(ms.getMatrix(), ms.getReposRow(), ms.getReposColumn());
			missedSku = drp.Validate(targetShopList);
		}
		
		// TODO 生成订单号
			for(int i = 0;i<targetShopList.size();i++) 
			 {
					try{
						targetShopList.get(i).setOrderId(GenerateIdUtil.ordernumber());
				 }catch (ParseException e){
						// TODO Auto-generated catch block
						System.out.println("Parse error");
					}
						targetShopList.get(i).setTime(new Date());
			}
		
		// TODO 将sql未查询到的sku补充到"NOT"子订单中
		if(order.getOriginOrderList().size()>targetShopList.size())
			ResultUtil.addSqlMissedSku(order, targetShopList, ms);
		// TODO 统一结束状态
		for(TargetShop t:targetShopList)
			t.setState("done");
		
		// TODO 生成结果集对象
		resultShop.setOriginOrderId(order.getOrderId());
		resultShop.setChildOrderList(targetShopList);
		// TODO 将结果集写入到订单拆分,明细,状态表
		orderSplitDao.insertOrderSplit(resultShop);
		orderInfoDao.insertResult(resultShop);
		orderStateDao.insertResultState(resultShop);
		stockDao.updateStockByResult(resultShop);
		// TODO 更改源订单状态为已拆分
		order.setOrderState("done");
		orderStateDao.UpdateOrderState(order);
		
		
		// TODO 移除NOShopMatched后的result进行库存变化插入
		for(TargetShop ts:resultShop.getChildOrderList())
		{
			if(!ts.getShopCode().equals("NoShopMatched")){	
				try {
					stockChangeDao.insertOneStockChange(ts);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("error during insert");
				}
			}
		}
		
		try {
			ExcelUtil.writeExcel("E:/test/结果信息.xls", resultShop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error !!!");
		}
		
		logger.info("Generating done");
		return resultShop;
	}

	
	
	public ShopSelectServiceImpl() {
		// TODO Auto-generated constructor stub
	}

}
