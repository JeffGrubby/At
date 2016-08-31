/**
 * 
 */
package com.ylmall.at.util;

/**
 * At.com.ylmall.at.util
 * @author JeffGrubb
 * @version 1.0
 * 2016下午2:19:54
 */
public class StringUtil {


	
/**
 * 
 * @param String
 * @return	Int
 * TODO	字符串转Int
 */
	public static int toInt(String s){
		int a = -1;
		try {
		    a = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			//写日志
			
		    e.printStackTrace();
		}
		
		return a;
	}
	/**
	 * 
	 * @param 矩阵
	 * @param 门店列表
	 * @param sku列表
	 * @return	矩阵字符串化的结果
	 * TODO	将矩阵信息输出
	 */
	public static String MatrixToStr(int[][] matrix,Object[] head,Object[] left){
		StringBuilder sb = new StringBuilder();
		sb.append('\n');
		for(int i=0;i<head.length;i++) {
			sb.append("\t"+head[i]);
		}
		sb.append('\n');
		for(int i=0;i<left.length;i++) {
			sb.append(left[i]+"\t");
			for(int j=0;j<head.length;j++) {
				sb.append("["+matrix[i][j]+"]\t");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
