/**
 * 
 */
package myTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ylmall.at.dao.OrderInfoDao;
import com.ylmall.at.dao.OrderSplitDao;
import com.ylmall.at.dao.OrderStateDao;
import com.ylmall.at.dao.StockChangeDao;
import com.ylmall.at.dao.StockDao;
import com.ylmall.at.model.SkuNeed;
import com.ylmall.at.service.MatrixAlgorithmService;
import com.ylmall.at.service.Impl.MatrixAlgorithmServiceImpl;
import com.ylmall.at.service.Impl.MatrixServiceImpl;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.ResultShop;
import com.ylmall.at.tobject.SelectedShop;
import com.ylmall.at.tobject.TargetShop;
import com.ylmall.at.util.ExcelUtil;
import com.ylmall.at.util.GenerateIdUtil;
import com.ylmall.at.util.ResultUtil;



/**
 * At.myTest
 * @author JeffGrubb
 * @version 1.0
 * 2016上午9:07:05
 */
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath:/spring-mybatis.xml"})

public class TestMyService {
	private static Logger logger = Logger.getLogger(TestMyService.class);

	private Order order;
	private ArrayList<SkuNeed> originOrderList = new ArrayList<SkuNeed>();
//	private MatrixService ms = null; 
	private MatrixAlgorithmService ma;
//	private List<SelectedShop> shopList = new ArrayList<SelectedShop>();
	private ResultShop resultShop;
	
	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private OrderStateDao orderStateDao;
	@Resource
	private OrderSplitDao orderSplitDao;
	@Resource
	private StockDao stockDao;
	@Resource
	private StockChangeDao stockChangeDao;

//	private DRPValidateService drp;
	@Before
	public void old(){
		originOrderList.add(new SkuNeed("sku1",10));
		originOrderList.add(new SkuNeed("sku2",50));
		originOrderList.add(new SkuNeed("sku3",30));
		order = new Order();
		order.setOrderId("A");
		order.setOrderState("ready");
		order.setOriginOrderList(originOrderList);
	}
	
	@Test  
    public void test1() {
		long before = System.currentTimeMillis();
	
		resultShop = new ResultShop();
		List<SelectedShop>	selectedShopList;
		List<TargetShop> targetShopList;
//		List<String> missedSku;
		MatrixServiceImpl ms;
		
		// TODO 对订单对象判空
		if(null==order||0==order.getOriginOrderList().size()){
			//输出日志
			logger.info("Order is null");
			return ;
		}
		logger.error("begin");
		//---------------------------------------------
		// TODO 查询订单明细表中此订单号对应shopCode
		List<String> shopCode=orderInfoDao.selectShopCode(order.getOrderId());
        logger.error(JSON.toJSONString(shopCode));
          
        //----------------------------------------------
		if(0==shopCode.size()){
			// TODO sql查询符合订单条件的全部门店集合(首次拆单)
			selectedShopList = stockDao.selectSkuShop(order);
			// TODO 对sql查询结果判空
			if(null==selectedShopList||0==selectedShopList.size()){
				//输出日志
				logger.error("NO SHOP SELECTED");
				return ;
			}
			for(SelectedShop s:selectedShopList)
				System.out.println(s.getShopCode()+"  "+s.getSku());
			// TODO 将订单对象插入订单状态，订单明细表
			try{
				orderStateDao.insertOrderState(order);}
			catch(Exception e){
				System.out.println("error during insert");	
			}
			orderInfoDao.insertOrderInfo(order);
			
		}
		else{
			// TODO 排除特定门店再筛选(重新拆单)
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("order", order.getOriginOrderList());
			map.put("shopCode", shopCode);
			selectedShopList = stockDao.reselectSkuShop(map);
			// TODO 对sql查询结果判空
			if(null==selectedShopList||0==selectedShopList.size()){
				//输出日志
				logger.error("NO SHOP SELECTED");
				return ;
			}

			
			// TODO 修改订单状态表状态为"待拆分"
			System.out.println(order.getOrderState());
			order.setOrderState("running");
			orderStateDao.UpdateOrderState(order);
			
			System.out.println(order.getOrderState());
		}
		
	
		//--------------------------------------------
				
		// TODO 初始化矩阵(order是为了赋值对应sku的数量)
		ms = new MatrixServiceImpl(order);
		ms.initMatrix(selectedShopList);

		// TODO 矩阵筛选
		ma = new MatrixAlgorithmServiceImpl(ms);
		targetShopList = ma.selectShop(ms.getMatrix(), ms.getReposRow(), ms.getReposColumn());
		
		// TODO 输出初始矩阵信息（日志打印）
//		logger.error(StringUtil.MatrixToStr(ms.getMatrix(), ms.getShopArray(), ms.getSkuArray()));
		
		// TODO DRP循环校验
		for(int i = 0;i<targetShopList.size();i++)
		{			
			 targetShopList.get(i).setOrderId("order"+i);
		}
		List<String> sku = new ArrayList<String>();
		//对应门店不匹配的sku
//	    sku.add("sku2");
//		sku.add("sku1");
		
		ma.modify(targetShopList,sku);
		targetShopList = ma.selectShop(ms.getMatrix(), ms.getReposRow(), ms.getReposColumn());
		

		// TODO 将sql未查询到的sku补充到"NOT"子订单中
		if(order.getOriginOrderList().size()>targetShopList.size())
				ResultUtil.addSqlMissedSku(order, targetShopList, ms);
		// TODO 统一结束状态
		for(TargetShop t:targetShopList)
			t.setState("done");
		
		// TODO 生成订单号
		for(int i = 0;i<targetShopList.size();i++)
		{
				try {
				targetShopList.get(i).setOrderId(GenerateIdUtil.ordernumber());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Parse error");
			}
				targetShopList.get(i).setTime(new Date());
		}
		
		// TODO 生成结果集对象
		resultShop.setOriginOrderId(order.getOrderId());
		resultShop.setChildOrderList(targetShopList);
		
		// TODO 输出结果
		System.out.println(resultShop.getOriginOrderId());
		for(TargetShop targetShop:resultShop.getChildOrderList())
		{
			System.out.print(targetShop.getOrderId()+" "+targetShop.getShopCode()+" "+targetShop.getState()+": ");
			for(SkuNeed s:targetShop.getInfoList())
				System.out.print(s.getSku()+" "+s.getAmount()+" ");
			System.out.println("");
		}
		
		
		// TODO 将结果集写入到订单拆分,明细,状态表
		try{
			orderSplitDao.insertOrderSplit(resultShop);
			orderInfoDao.insertResult(resultShop);
			orderStateDao.insertResultState(resultShop);		
			stockDao.updateStockByResult(resultShop);
			// TODO 更改源订单状态为已拆分
			order.setOrderState("gone");
			orderStateDao.UpdateOrderState(order);
		   }
		catch(Exception e){
			System.out.println("error during insert");	
		}
		
		
		// TODO 移除NOMatched后的result进行库存变化插入
		long b = System.currentTimeMillis();
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
		long a = System.currentTimeMillis();
		System.out.println("------------------------");
		System.out.println("time="+(a-b)+"millis");
		
		long after = System.currentTimeMillis();
		System.out.println("------------------------");
		System.out.println("time="+(after-before)+"millis");
		
		
		try {
			ExcelUtil.writeExcel("E:/test/结果信息.xls", resultShop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error !!!");
		}
    }  
}
