/**
 * 
 */
package myTest;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ylmall.at.dao.OrderInfoDao;
import com.ylmall.at.dao.OrderStateDao;
import com.ylmall.at.dao.ShopDao;
import com.ylmall.at.dao.StockDao;
import com.ylmall.at.model.Shop;
import com.ylmall.at.model.Stock;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.util.ExcelUtil;

/**
 * At.myTest
 * @author JeffGrubb
 * @version 1.0
 * 2016����4:56:00
 */
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath:/spring-mybatis.xml"})
public class TestMyExcelRead {
	@Resource
	private StockDao stockDao;
	@Resource
	private ShopDao shopDao;
	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private OrderStateDao orderStateDao;
	
	// TODO ��ȡ���� д�����ݿ�
	@Test
	public void readExcel(){
		try {
			@SuppressWarnings("unchecked")
			List<Stock> s = (List<Stock>) ExcelUtil.readExcel("E:/test/�����Ϣ.xls");
			stockDao.insertStock(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	// TODO����ȡ�ŵ�� д�����ݿ�
	@Test
	public void readExcel2(){
		try {
			@SuppressWarnings("unchecked")
			List<Shop> s = (List<Shop>) ExcelUtil.readExcel("E:/test/shop_222.xls");
			shopDao.insertShop(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

	
	// TODO����ȡ�������ӱ� д�����ݿ�
	@Test
	public void readExcel3(){
		try {
			List<Order> s = (List<Order>) ExcelUtil.readExcel("E:/test/53_order.xls","E:/test/53_item.xls");
			for(Order order:s){
				
				orderInfoDao.insertOrderInfo(order);
				orderStateDao.insertOrderState(order);
			}
//			for(Order order:s){
//				for(SkuNeed sk:order.getOriginOrderList())
//					System.out.println(order.getOrderId()+"  "+sk.getSku()+" "+order.getOrderState());
//				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	// TODO ��Excel
	@Test
	public void create(){
		try {
			ExcelUtil.createExcel("E:/test/�����Ϣ.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
