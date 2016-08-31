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
 * 2016����9:07:05
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
		
		// TODO �Զ��������п�
		if(null==order||0==order.getOriginOrderList().size()){
			//�����־
			logger.info("Order is null");
			return ;
		}
		logger.error("begin");
		//---------------------------------------------
		// TODO ��ѯ������ϸ���д˶����Ŷ�ӦshopCode
		List<String> shopCode=orderInfoDao.selectShopCode(order.getOrderId());
        logger.error(JSON.toJSONString(shopCode));
          
        //----------------------------------------------
		if(0==shopCode.size()){
			// TODO sql��ѯ���϶���������ȫ���ŵ꼯��(�״β�)
			selectedShopList = stockDao.selectSkuShop(order);
			// TODO ��sql��ѯ����п�
			if(null==selectedShopList||0==selectedShopList.size()){
				//�����־
				logger.error("NO SHOP SELECTED");
				return ;
			}
			for(SelectedShop s:selectedShopList)
				System.out.println(s.getShopCode()+"  "+s.getSku());
			// TODO ������������붩��״̬��������ϸ��
			try{
				orderStateDao.insertOrderState(order);}
			catch(Exception e){
				System.out.println("error during insert");	
			}
			orderInfoDao.insertOrderInfo(order);
			
		}
		else{
			// TODO �ų��ض��ŵ���ɸѡ(���²�)
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("order", order.getOriginOrderList());
			map.put("shopCode", shopCode);
			selectedShopList = stockDao.reselectSkuShop(map);
			// TODO ��sql��ѯ����п�
			if(null==selectedShopList||0==selectedShopList.size()){
				//�����־
				logger.error("NO SHOP SELECTED");
				return ;
			}

			
			// TODO �޸Ķ���״̬��״̬Ϊ"�����"
			System.out.println(order.getOrderState());
			order.setOrderState("running");
			orderStateDao.UpdateOrderState(order);
			
			System.out.println(order.getOrderState());
		}
		
	
		//--------------------------------------------
				
		// TODO ��ʼ������(order��Ϊ�˸�ֵ��Ӧsku������)
		ms = new MatrixServiceImpl(order);
		ms.initMatrix(selectedShopList);

		// TODO ����ɸѡ
		ma = new MatrixAlgorithmServiceImpl(ms);
		targetShopList = ma.selectShop(ms.getMatrix(), ms.getReposRow(), ms.getReposColumn());
		
		// TODO �����ʼ������Ϣ����־��ӡ��
//		logger.error(StringUtil.MatrixToStr(ms.getMatrix(), ms.getShopArray(), ms.getSkuArray()));
		
		// TODO DRPѭ��У��
		for(int i = 0;i<targetShopList.size();i++)
		{			
			 targetShopList.get(i).setOrderId("order"+i);
		}
		List<String> sku = new ArrayList<String>();
		//��Ӧ�ŵ겻ƥ���sku
//	    sku.add("sku2");
//		sku.add("sku1");
		
		ma.modify(targetShopList,sku);
		targetShopList = ma.selectShop(ms.getMatrix(), ms.getReposRow(), ms.getReposColumn());
		

		// TODO ��sqlδ��ѯ����sku���䵽"NOT"�Ӷ�����
		if(order.getOriginOrderList().size()>targetShopList.size())
				ResultUtil.addSqlMissedSku(order, targetShopList, ms);
		// TODO ͳһ����״̬
		for(TargetShop t:targetShopList)
			t.setState("done");
		
		// TODO ���ɶ�����
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
		
		// TODO ���ɽ��������
		resultShop.setOriginOrderId(order.getOrderId());
		resultShop.setChildOrderList(targetShopList);
		
		// TODO ������
		System.out.println(resultShop.getOriginOrderId());
		for(TargetShop targetShop:resultShop.getChildOrderList())
		{
			System.out.print(targetShop.getOrderId()+" "+targetShop.getShopCode()+" "+targetShop.getState()+": ");
			for(SkuNeed s:targetShop.getInfoList())
				System.out.print(s.getSku()+" "+s.getAmount()+" ");
			System.out.println("");
		}
		
		
		// TODO �������д�뵽�������,��ϸ,״̬��
		try{
			orderSplitDao.insertOrderSplit(resultShop);
			orderInfoDao.insertResult(resultShop);
			orderStateDao.insertResultState(resultShop);		
			stockDao.updateStockByResult(resultShop);
			// TODO ����Դ����״̬Ϊ�Ѳ��
			order.setOrderState("gone");
			orderStateDao.UpdateOrderState(order);
		   }
		catch(Exception e){
			System.out.println("error during insert");	
		}
		
		
		// TODO �Ƴ�NOMatched���result���п��仯����
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
			ExcelUtil.writeExcel("E:/test/�����Ϣ.xls", resultShop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error !!!");
		}
    }  
}
