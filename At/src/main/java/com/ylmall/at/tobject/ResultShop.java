package com.ylmall.at.tobject;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * At.com.ylmall.at.tobject
 * @author JeffGrubb
 * @version 1.0
 * 2016ÏÂÎç1:00:15
 */
public class ResultShop {

	

	private String originOrderId;
	private List<TargetShop> childOrderList;
	
	public ResultShop() {
		// TODO Auto-generated constructor stub
		childOrderList = new ArrayList<TargetShop>();
	}

	public String getOriginOrderId() {
		return originOrderId;
	}

	public void setOriginOrderId(String originOrderId) {
		this.originOrderId = originOrderId;
	}

	public List<TargetShop> getChildOrderList() {
		return childOrderList;
	}

	public void setChildOrderList(List<TargetShop> childOrderList) {
		this.childOrderList = childOrderList;
	}
}
