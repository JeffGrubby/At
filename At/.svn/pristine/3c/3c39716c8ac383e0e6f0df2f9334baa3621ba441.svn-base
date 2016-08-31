/**
 * 
 */
package com.ylmall.at.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ylmall.at.service.ShopSelectService;
import com.ylmall.at.tobject.Order;

/**
 * At.com.ylmall.at.controller
 * @author JeffGrubb
 * @version 1.0
 * 2016ÏÂÎç2:45:30
 */
@Controller
@RequestMapping("/test")  
public class TestController {
	@Resource
	private ShopSelectService service;
	
	 @RequestMapping("/showTest")
	 public String result(HttpServletRequest request,Model model){  
	        String orderNo = request.getParameter("id");  
	        
	        
	        return "showTest";  
	    }  
	
}
