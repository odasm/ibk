package com.javalitterboy.ibk.utils;

import java.util.Random;

/**
 * @author 14183
 */
public class Util {

    /**
     * 随机指定长度字符串 数字加大写字母
     * @param len 指定字符串长度
     * @return
     */
    public static String random_str(int len){
        StringBuilder stringBuffer = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for(int i=0;i<len;i++){
            stringBuffer.append(Integer.toString(random.nextInt(36),36));
        }
        return stringBuffer.toString();
    }
}
