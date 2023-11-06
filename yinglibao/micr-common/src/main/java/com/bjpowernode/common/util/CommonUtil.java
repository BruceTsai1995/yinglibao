package com.bjpowernode.common.util;

import java.util.regex.Pattern;

public class CommonUtil {
    /**
     * 处理pageNo
     */
    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        if (pageNo == null || pageNo < 1) {
            pNo = 1;
        }
        return pNo;
    }

    /**
     * 处理pageSize
     */
    public static int defaultPageSize(Integer pageSize) {
        int pSize = pageSize;
        if (pageSize == null || pageSize < 1) {
            pageSize = 1;
        }
        return pSize;
    }


    /**
     * 手机号脱去敏感信息处理
     */
    public static String tuoMinPhone(String phone) {
        String result = "***********";
        if (phone != null && phone.trim().length() == 11) {
            result = phone.substring(0, 3) + "******" + phone.substring(9, 11);
        }
        return result;
    }

    /**
     * 检查手机号的格式
     * true:日本手机号格式正确
     * false:格式不正确
     */
    public static boolean checkPhone(String phone){
        boolean flag = false;
        if (phone != null &&phone.length() == 11) {
            String regex = "^(\\+81|0)[1-9]\\d{8,9}$";
            flag = Pattern.matches(regex, phone);
        }
        return flag;
    }

}
