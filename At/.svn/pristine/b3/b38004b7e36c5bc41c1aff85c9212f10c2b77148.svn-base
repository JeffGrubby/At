/**
 * 
 */
package com.ylmall.at.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

/**
 * At.com.ylmall.at.util
 * @author JeffGrubb
 * @version 1.0
 * 2016下午1:51:41
 */
public class GenerateIdUtil {


    /**
     * 订单号
    * @return
    * @throws ParseException
     */
    public static String ordernumber() throws ParseException{
        String orderno = "DD" + DateConvertUtil.formateString(new Date(), "yyyyMMddHHmmssSSS");
        orderno= orderno+randomFive();
        return orderno;
    }
    /**
     * 生成5位随机数
    * @return
     */
    public static int randomFive() {
        Random random = new Random();
        int result = random.nextInt(100000);
        if (String.valueOf(result).length() == 5) {
            return result;
        }
        return randomFive();
    }
}
