package com.example.ruifight_3.saolouruifight.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RuiFight-3 on 2018/7/24.
 */

public class Validator {

    /**
     * 手机号号段校验，
     第1位：1；
     第2位：{3、4、5、6、7、8 、9}任意数字；
     第3—11位：0—9任意数字
     * @param value
     * @return
     */
    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[2|3|4|5|6|7|8|9][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }
    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     */
    public static boolean isLegalName(String name){
        if (name.contains("·") || name.contains("•")){
            if (name.matches("^[\\u4e00-\\u9fa5]{2,4}+[·•][\\u4e00-\\u9fa5]{2,3}$")){
                return true;
            }else {
                return false;
            }
        }else {
            if (name.matches("^[\\u4e00-\\u9fa5]{2,5}$")){
                return true;
            }else {
                return false;
            }
        }
    }
    /**
     * 验证输入的身份证号是否合法
     */
    public static boolean isLegalId(String id){
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")){
            return true;
        }else {
            return false;
        }
    }
}
