/**
 * 
 */
package com.ylmall.at.util;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;  

/**
 * At.com.ylmall.at.util
 * @author JeffGrubb
 * @version 1.0
 * 2016下午1:46:29
 */
public class AtClientUtil {
	
	/**
	 * @param WebService的URL
	 * @return
	 * TODO 初始化调用客户端
	 */
	  private static RPCServiceClient initServiceClient(String serviceURL){
	     //  使用RPC方式调用WebService	
	  RPCServiceClient serviceClient;
	    try {
	      serviceClient = new RPCServiceClient();
	      Options options = serviceClient.getOptions();
	      //  指定调用WebService的URL
	      EndpointReference targetEPR = new EndpointReference(serviceURL);
	      options.setTo(targetEPR);
	      return serviceClient;
	    } catch (AxisFault e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	  
	/**
	 * 
	 * @param WebService的URL
	 * @param methodName方法返回值的数据类型的Class对象
	 * @param 调用的methodName方法
	 * @param methodName方法的参数值
	 * @return
	 * TODO
	 */
	  public static Object[] callService(String serviceURL,Class type,String methodName,String args){
	    
	      try {
	      	RPCServiceClient service2Client = initServiceClient(serviceURL);
	      	//  指定methodName方法的参数值
	      	Object[] opAddEntryArgs = new Object[] {args};
	      	//  指定methodName方法返回值的数据类型的Class对象
	      	Class[] classes = new Class[] {type};
	      	//  指定要调用的methodName方法及WSDL文件的命名空间
	      	QName opAddEntry = new QName("http://Impl.service.at.ylmall.com", methodName);
	      return service2Client.invokeBlocking(opAddEntry, opAddEntryArgs,classes);
	    } catch (AxisFault e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	      return null;
	  }

}
