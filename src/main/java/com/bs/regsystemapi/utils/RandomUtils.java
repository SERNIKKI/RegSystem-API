package com.bs.regsystemapi.utils;

import java.util.Random;

/**
 * @Author qpj
 * @Date: 2022/02/21/ 22:18
 * @Description
 */
public class RandomUtils {
    public static String getrandom(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10); //每次随机出一个数字（0-9）
            code = code + r;  //把每次随机出的数字拼在一起
        }
        return code;
    }

    public static String getrandom(int size){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int r = random.nextInt(10); //每次随机出一个数字（0-9）
            code = code + r;  //把每次随机出的数字拼在一起
        }
        return code;
    }
}
