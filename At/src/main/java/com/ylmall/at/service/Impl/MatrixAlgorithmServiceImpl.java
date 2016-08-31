package com.ylmall.at.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ylmall.at.model.SkuNeed;
import com.ylmall.at.service.MatrixAlgorithmService;
import com.ylmall.at.tobject.TargetShop;
import com.ylmall.at.util.ResultUtil;

@Service("matrixAlgorithmService")
public class MatrixAlgorithmServiceImpl implements MatrixAlgorithmService{
	private Logger logger = LogManager.getLogger(MatrixAlgorithmServiceImpl.class.getName()); 
	private  MatrixServiceImpl ma;

	
	private boolean rowState = true;
	
	/**
	 * 
	 */
	public MatrixAlgorithmServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public MatrixAlgorithmServiceImpl(MatrixServiceImpl ma) {
		// TODO Auto-generated constructor stub
		this.ma = ma;
	}
	
	/**
	* @param matrix
	* @param ReposRow
	* @param ReposColumn
	* @return
	* TODO ɸѡ����
	*/
	public  List<TargetShop> selectShop(int[][] matrix, int[] ReposRow, int[] ReposColumn) {
		logger.info("Selecting Shop");
		TargetShop temp ;
		SkuNeed skuNeed;
		List<TargetShop> targetShopList = new ArrayList<TargetShop>();
		int[] rowIgnore = ReposRow.clone();
		int[] columnIgnore = ReposColumn.clone();
		
		
		//skuȫ�����޻�
		ResultUtil.addMissedSku(targetShopList, ma, rowIgnore, matrix);
				
		//������5�Ų�δ��ƥ�� ��д������
		if(ma.getReposState())
		{
			temp = new TargetShop();
			for(int i=0;i<ReposColumn.length;i++)
			{
				//�ҵ�5�Ų����о͸�ֵ������ѭ��
				if(ReposColumn[i]==-1)
				{
					
					temp.setShopCode(ma.getShopArray()[i].toString());
					for(int j=0;j<ReposRow.length;j++)
						if(matrix[j][i]==1)
						{								
							skuNeed = new SkuNeed();
							skuNeed.setSku(ma.getSkuArray()[j].toString());
							skuNeed.setAmount(ma.getAmountArray()[j]);
							//System.out.println(ma.getSkuArray()[j].toString()+" "+ma.getAmountArray()[j]);
							temp.getInfoList().add(skuNeed);
							// temp.getSku().add(ma.getSkuArray()[j].toString());	
						}
					targetShopList.add(temp);
					break;
				}
			}
			
		}
		
		
		rowState = false ;
				
		for(int i=0;i<rowIgnore.length;i++){
			if(rowIgnore[i]!=-1) {rowState=true;break;}
			}
		
		while(rowState){	
				rowState=false;
				int p = sumColum(rowIgnore,columnIgnore);
				//����p���ŵ�
				columnIgnore[p]=-1;
				temp = new TargetShop();
				//�����ŵ���
				temp.setShopCode(ma.getShopArray()[p].toString());
				for(int i=0;i<rowIgnore.length;i++)
				{
					if(rowIgnore[i]!=-1&&matrix[i][p]==1)
						{
						//�����ŵ��Ӧsku
						skuNeed = new SkuNeed();
						skuNeed.setSku(ma.getSkuArray()[i].toString());
						skuNeed.setAmount(ma.getAmountArray()[i]);
						temp.getInfoList().add(skuNeed);
					//	temp.getSku().add(ma.getSkuArray()[i].toString());
						rowIgnore[i]=-1;
						}
				}
				
				targetShopList.add(temp);
				
				for(int i=0;i<rowIgnore.length;i++)
				{
					if(rowIgnore[i]!=-1) {rowState=true;break;}
				}
			}
		
	//չʾ	
		for(TargetShop target :targetShopList){
			for(int i =0;i<target.getInfoList().size();i++)
				System.out.println(target.getInfoList().get(i).getSku()+"  "+target.getShopCode()+"  "
						+target.getInfoList().get(i).getAmount());	
			}
		logger.error("Selecting done");
		return targetShopList;
	}
	
	/**
	 * 
	 * @param ValidatedTargetShop
	 * @param missedSku
	 * @return
	 * TODO ����ɸѡǰ�Ծ�������,�޸ĺ�������
	 */
	public  boolean modify(List<TargetShop> ValidatedTargetShop,List<String> missedSku) {
		logger.info("Running modify");
		if(missedSku==null||0==missedSku.size()){
		//	System.out.println("nope");
			return true;
		}
		for(TargetShop targetShop:ValidatedTargetShop){
			if(targetShop.getOrderId()==null){
				for(int i= 0;i<ma.getShopArray().length;i++)
				{
					if(ma.getShopArray()[i].equals(targetShop.getShopCode()))
					{
						for(int j=0;j<ma.getSkuArray().length;j++)	
							for(int k =0;k<missedSku.size();k++)
								if(ma.getSkuArray()[j].equals(missedSku.get(k)) )
								{
									ma.setMatrix(j, i, 0);
									if(ma.getShopArray()[i].equals("Repos"))
									{
										ma.setReposRow(j, 0);
										
									}		
								}
						break;
					}
				}
				//TODO ������
				for(int i=0;i<ma.getShopArray().length;i++) {
						System.out.print("\t"+ma.getShopArray()[i]);
					}
					System.out.println("");
					for(int i=0;i<ma.getSkuArray().length;i++) {
						System.out.print(ma.getSkuArray()[i]+"\t");
						for(int j=0;j<ma.getShopArray().length;j++) {
							System.out.print("["+ma.getMatrix()[i][j]+"]\t");
						}
						System.out.println("");
					}
					logger.info("modify done");
	
				rowState =true;
				return false;
			}
		}
		rowState = false;
		return true;
	}

	
	/**
	 * TODO �󻮷�����������(���ⲿ����ѡ��--private)
	 * @param row
	 * @param column
	 * @return
	 */
	private int sumColum(int[] row,int[] column){
		int maxNum = 0;
		int maxColumn = 0;
		int temp =0;
		
		for(int i=0;i<column.length;i++)
		{	
			if(column[i]!=-1)
			{
				for(int j=0;j<row.length;j++)
				{
						if(row[j]!=-1) 
						 temp += ma.getMatrix()[j][i];
					
				}
				if(temp>maxNum) {maxNum = temp;maxColumn=i;}
				temp=0;
				
			}

		}
		return maxColumn;
	}

	


}