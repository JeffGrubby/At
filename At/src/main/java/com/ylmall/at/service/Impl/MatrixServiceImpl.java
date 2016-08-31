/**
 * 
 */
package com.ylmall.at.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ylmall.at.model.SkuNeed;
import com.ylmall.at.service.MatrixService;
import com.ylmall.at.tobject.Order;
import com.ylmall.at.tobject.SelectedShop;



/**
 * At.com.ylmall.at.service.Impl
 * @author JeffGrubb
 * @version 1.0
 * 2016����11:53:43
 */
@Service("matrixService")
public class MatrixServiceImpl implements MatrixService{
	
	private Logger logger = LogManager.getLogger(MatrixServiceImpl.class.getName());
	private   int[][] matrix;
	
	private  Object[] skuArray ;
	private  Object[] shopArray ;
	private  int[] amountArray;
	private  int[] ReposRow;
	private  int[] ReposColumn;
	private  boolean ReposState;
	private Order order;
	/**
	 * 
	 */
	public MatrixServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param shopList
	 * TODO ��ʼ�����������
	 */
	public void initMatrix(List<SelectedShop> shopList) {
			logger.info("initating Matrix");
			 Set<String> skuNum = new HashSet<String>();
				for(SelectedShop param :shopList) {
					skuNum.add(param.getSku());
				}
				shopArray = new Object[shopList.size()];
				for(int i=0;i<shopList.size();i++){
					shopArray[i]=shopList.get(i).getShopCode();		
				}
				int row;
				int column;
				
				row=skuNum.size();
				column=shopArray.length;
				
				skuArray =skuNum.toArray();
				amountArray = new int[skuArray.length];
				
				// TODO �Ӷ��������л�ȡsku����
				for(int i = 0;i<skuArray.length;i++){
					for(SkuNeed s:order.getOriginOrderList())
						if(skuArray[i].equals(s.getSku()))
							amountArray[i]= s.getAmount();
				}
			
				
				ReposRow = new int[row];
				ReposColumn = new int[column];
				
				//TODO ����ֵ
				matrix = new int[row][column];
				for(SelectedShop param :shopList) {
					int i,j;
					for(i=0;i<row;i++) {
						if(skuArray[i].toString().equals(param.getSku())) {
							break;
						}
					}
					
					for(j=0;j<column;j++) {
						if(shopArray[j].toString().equals(param.getShopCode())) {
							break;
						}
					}
					matrix[i][j] = 1;
				}
				//�����5�Ų�		
				for(Object t:shopArray)
				if(t.toString().equals("Repos"))
				{
					ReposFun();
					
				}
				//��־���
				logger.info("initating done");
				
				//TODO ������
			for(int i=0;i<column;i++) {
					System.out.print("\t"+shopArray[i]);
				}
				System.out.println("");
				for(int i=0;i<row;i++) {
					System.out.print(skuArray[i]+"\t");
					for(int j=0;j<column;j++) {
						System.out.print("["+matrix[i][j]+"]\t");
					}
					System.out.println("");
				}
			
		
	}
	
	//��5�Ųֺ�������(���ⲿ����ѡ��--private)
	private  void ReposFun(){
					ReposState = true;
					for(int i=0;i<shopArray.length;i++)
					{
						//�ҵ�5�Ųֺ����������ѭ��
						if(shopArray[i].equals("Repos"))
						{
							ReposColumn[i]=-1;
							for(int j=0;j<skuArray.length;j++)
							{
								if(matrix[j][i]==1)
									ReposRow[j]=-1;
							}
							break;
						}
						
					}
				}

	public MatrixServiceImpl(Order order) {
		// TODO Auto-generated constructor stub
		this.order = order;
	}

	public int[] getAmountArray() {
		return amountArray;
	}

	public void setAmountArray(int[] amountArray) {
		this.amountArray = amountArray;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int i,int j,int num) {
		this.matrix[i][j] = num;
	}

	public Object[] getSkuArray() {
		return skuArray;
	}

	public void setSkuArray(Object[] skuArray) {
		this.skuArray = skuArray;
	}

	public Object[] getShopArray() {
		return shopArray;
	}

	public void setShopArray(Object[] shopArray) {
		this.shopArray = shopArray;
	}

	public int[] getReposRow() {
		return ReposRow;
	}

	public void setReposRow(int row,int num) {
		this.ReposRow[row] = num;
	}

	public int[] getReposColumn() {
		return ReposColumn;
	}

	public void setReposColumn(int[] reposColumn) {
		ReposColumn = reposColumn;
	}

	public boolean getReposState() {
		return ReposState;
	}

	public void setReposState(boolean reposState) {
		ReposState = reposState;
	}

}