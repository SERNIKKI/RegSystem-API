package com.bs.regsystemapi.utils;

import cn.hutool.core.map.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 针对Mybatis select到的List<Map>数据的工具类
 */
public class ListMapUtils {

    /**
     *将Map的键转换为驼峰形式
     * @param data
     * @return
     */
    public static List<Map<String,Object>> customKeyListMap(List<Map<String,Object>> data){

        List<Map<String,Object>> newData=new ArrayList<Map<String,Object>>();

        for(Map<String,Object> map : data)
            newData.add(MapUtil.toCamelCaseMap(map));

        return newData;
    }

    /**
     * 在Map中添加指定键为默认值
     * @param data 需要添加的List
     * @param value 为键指定的默认值
     * @param keys 键名称
     * @return
     */
    public static List<Map<String,Object>> addKeyValue(List<Map<String,Object>> data,String value,String ...keys){

        for(Map<String,Object> map : data){

            for(String key:keys) {

                if (!map.containsKey(key))
                    map.put(key,value);
            }
        }
        return data;
    }

    /**
     * 纵向计算List<Map>中相同键的值，并以Map形式返回
     * @param data  源数据
     * @param keys  需要计算总计键值
     * @return 将统计后的数据以Map形式返回
     */
    public static Map<String,Object> getTotal(List<Map<String,Object>> data,String ...keys){

        Map<String,Object> newMap=new HashMap<>();

        for(String key : keys)
            newMap.put(key,new Double(0));

        for(Map<String,Object> map : data){

            for(String key : keys){

              Object n1=map.get(key);
              if(n1 != null){
                  Double d1= Convert.toDouble(n1);
                  Double d2=d1 + (Double) newMap.get(key);
                  newMap.put(key,d2);
              }
            }
        }

        return newMap;
    }

    /**
     * 纵向计算List<Map>中相同键的值，添加新Map到List中
     * @param data 源数据
     * @param rowName 计算结果行的行名称
     * @param keys 需要计算总计键值
     * @return
     */
    public static List<Map<String,Object>> addTotal(
            List<Map<String,Object>> data,String rowName,String ...keys){

        Map<String,Object> newMap=getTotal(data,keys);

        newMap.put(rowName,"总计");

        data.add(newMap);

        return data;
    }
}
