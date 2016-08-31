/**
 * 
 */
package com.ylmall.at.model;

import java.util.Date;

/**
 * At.com.ylmall.at.model
 * @author JeffGrubb
 * @version 1.0
 * 2016ÏÂÎç3:00:29
 */
public class Rule {

	/**
	 * 
	 */
	private int ruleId;
	private Date startTime;
	private Date dueTime;
	private double money;
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public Rule() {
		// TODO Auto-generated constructor stub
	}

}
