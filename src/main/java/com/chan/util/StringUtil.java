package com.chan.util;

/**
 * @author bronchan
 * @ClassName StringUtil
 * @date 2021/8/1 16:51
 * @Version 1.0
 * @Description TODO
 */
public class StringUtil {

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/1 16:56
    *@Param [str]
    *@Return boolean
    */
    public static boolean isEmpty(String str){
        if (str == null || str.equals("")) {
            return true;
        }else {
            return false;
        }
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/1 17:02
    *@Param [str]
    *@Return boolean
    */
    public static boolean isInteger(String str){
        try {
            Integer.parseInt(str);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
    *@Author chenqitao
    *@Description
    *@Date 2021/8/1 17:03
    *@Param [str]
    *@Return java.lang.Integer
    */
    public static Integer toInteger(String str){
        return Integer.parseInt(str);
    }
}
