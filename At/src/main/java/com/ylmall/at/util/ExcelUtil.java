/**
 * 
 */
package com.ylmall.at.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ylmall.at.model.Shop;
import com.ylmall.at.model.SkuNeed;
import com.ylmall.at.model.Stock;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.ResultShop;
import com.ylmall.at.tobject.TargetShop;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


/**
 * At.com.ylmall.at.util
 * @author JeffGrubb
 * @version 1.0
 * 2016下午3:37:16
 */
public class ExcelUtil {

	private static final String[] itemColumn={"原始订单号","新订单号","sku","数量","门店"};
	
	/**
	 * 
	 * @param 文件路径
	 * @param 拆分结果
	 * @throws Exception
	 * TODO
	 */
	public static void writeExcel(String path,ResultShop resultShop) throws Exception{	   
	        WritableWorkbook workbook = Workbook.createWorkbook(new File(path)); 
	        // TODO 获得第一个工作表对象
	        WritableSheet sheet = workbook.createSheet("sheet1", 0);  
	        Label lab = null;
	        // TODO 创建拆单结果Excel
	        if(path.contains("结果")){
	        	// TODO 添加第一行
	        	for(int k=0;k<itemColumn.length;k++){
	        		lab= new Label(k,0,itemColumn[k]);
	        		sheet.addCell(lab);
	        	}
	        	// TODO 添加数据
	        	int tempRow=1;
	        	for(TargetShop t:resultShop.getChildOrderList()){
	        		for(SkuNeed s:t.getInfoList()){
	        			lab= new Label(0,tempRow,resultShop.getOriginOrderId());
	        			sheet.addCell(lab);
	        			lab= new Label(1,tempRow,t.getOrderId());
	        			sheet.addCell(lab);
	        			lab= new Label(2,tempRow,s.getSku());
	        			sheet.addCell(lab);
	        			lab= new Label(3,tempRow,s.getAmount()+"");
	        			sheet.addCell(lab);
	        			lab = new Label(4,tempRow,t.getShopCode());
	        			sheet.addCell(lab);
	        			tempRow++;
	        		}
	        	}
	        }   
	        workbook.write();  
	        workbook.close();  
	      
	}
	
	
	/**
	 * 
	 * @param 要读取的文件路径
	 * @return 库存列表 门店列表
	 * @throws Exception
	 * TODO
	 */
	public static List<?> readExcel(String path) throws Exception{
		
            Workbook book = Workbook.getWorkbook(new File(path));
            // TODO 获得第一个工作表对象        
            Sheet sheet = book.getSheet(0);
            // TODO 读取库存表
            if(path.contains("库存"))
            {
            	List<Stock> sl = new ArrayList<Stock>();
            	Stock s = new Stock();
            	for(int i=2;i<sheet.getRows();i++)
            	{
            		for(int j=0;j<sheet.getColumns();j++)
            		{
			            Cell c = sheet.getCell(j,i);
			            switch(j)
			            {
				            case 0: s.setShopCode(c.getContents());break;
				            case 2: s.setSku(c.getContents());break;
				            case 7: s.setAmount(StringUtil.toInt(c.getContents()));break;
				            default:;
			            }
            		}
            		sl.add(s);
            		s= new Stock();//注意重新创建
            	}
            	book.close();
            	return sl;
            }
            
            else if(path.contains("shop")) {
            	List<Shop> s2 = new ArrayList<Shop>();
            	Shop s = new Shop();
            	for(int i=2;i<sheet.getRows();i++)
            	{
            		for(int j=0;j<sheet.getColumns();j++)
            		{
			            Cell c = sheet.getCell(j,i);
			            switch(j)
			            {
				            case 2: s.setShopCode(c.getContents());break;
				            case 5: s.setCity(c.getContents());;break;    
				            default:;
			            }
            		}
            		s2.add(s);
            		s= new Shop();//注意重新创建
            	}
            	book.close();
            	return s2;
            }
            
            else if(path.contains("order")){
            	
            	
            }
            
              
            book.close();
            return null;
	}
	
	
	/**
	 * 
	 * @param 订单主表
	 * @param 订单子表
	 * @return 订单列表对象
	 * @throws Exception
	 * TODO	获取订单列表
	 */
	public static List<Order> readExcel(String order,String item) throws Exception{

		 Workbook book_one = Workbook.getWorkbook(new File(order));
		 Workbook book_two = Workbook.getWorkbook(new File(item));
         // TODO 获得第一个工作表对象        
         Sheet sheet_one = book_one.getSheet(0);
         Sheet sheet_two = book_two.getSheet(0);
         
         List<Order> orderList = new ArrayList<Order>();
         Order temp = new Order();
       
         
         for(int i=1;i<sheet_two.getRows();i++){
        	 Cell c_two = sheet_two.getCell(9, i);
        
        	 for(int j=1;j<sheet_one.getRows();j++){
        		 Cell c_one = sheet_one.getCell(0, j);
        		 if(c_two.getContents().equals(c_one.getContents())){
        			 //订单号
        			 c_one = sheet_one.getCell(1, j);
        			 temp.setOrderId(c_one.getContents());
        			 //默认门店号为空
        			 temp.setShopCode(null);
        			 //订单状态
        			 c_two = sheet_two.getCell(17, i);
        			 temp.setOrderState(c_two.getContents());
        			 //sku 数量
        			 ArrayList<SkuNeed> s = new ArrayList<SkuNeed>();
        			 
            		 c_two = sheet_two.getCell(1, i);
            		 String sku = c_two.getContents();
            		 
            		 c_two = sheet_two.getCell(6, i);
            		 int num =StringUtil.toInt(c_two.getContents());
            		 s.add(new SkuNeed(sku,num));
            		
            		 
            		 while(i<(sheet_two.getRows()-1)){
	            		 i++;
	            		 if(sheet_two.getCell(9,i).getContents().equals(c_one.getContents())){
	
	                		 c_two = sheet_two.getCell(1, i);
	                		  sku = c_two.getContents();
	                		 
	                		 c_two = sheet_two.getCell(6, i);
	                		num =StringUtil.toInt(c_two.getContents());
	                		 s.add(new SkuNeed(sku,num));
	            		 	}
	            			 
	            		 else break;
            		  }
            	
            		 
            		 temp.setOriginOrderList(s);
            		 // TODO 添加到订单对象列表中
            		 orderList.add(temp);
            		 temp = new Order();
            		 
        			 break;
        		 }
        		 
        	 }
         }
		return orderList;
	}
	


	
	
}
