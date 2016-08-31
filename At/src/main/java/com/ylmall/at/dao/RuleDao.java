/**
 * 
 */
package com.ylmall.at.dao;

import java.util.Date;

/**
 * At.com.ylmall.at.dao
 * @author JeffGrubb
 * @version 1.0
 * 2016上午10:07:51
 */
public interface RuleDao {
	/**查询订单时间是否在规则时间段内*/
	boolean AmongTime(Date time);
}
