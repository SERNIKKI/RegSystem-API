package com.bs.regsystemapi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bs.regsystemapi.controller.LogController;
import com.bs.regsystemapi.modal.dto.QuerySuggestionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author qpj
 * @date 2022/2/25 16:27
 */
public class IpToAddressUtil {

    private static final Logger logger = LoggerFactory.getLogger(IpToAddressUtil.class);

    //使用腾讯的接口通过ip拿到城市信息
    private static final String KEY = "WNRBZ-VOJWP-HQFDL-LSO56-73JZ2-XEBQ5";
    public static String getCityInfo(String ip)  {
        String s = sendGet(ip, KEY);
        Map map = JSONObject.parseObject(s, Map.class);
        String message = (String) map.get("message");
        if("query ok".equals(message)){
            Map result = (Map) map.get("result");
            Map addressInfo = (Map) result.get("ad_info");
            String nation = (String) addressInfo.get("nation");
            String province = (String) addressInfo.get("province");
            //  String district = (String) addressInfo.get("district");
            String city = (String) addressInfo.get("city");
            String address = nation + "-" + province + "-" + city;
            return address;
        }else{
            System.out.println(message);
            return null;
        }
    }
    //根据在腾讯位置服务上申请的key进行请求操作
    public static String sendGet(String ip, String key) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "https://apis.map.qq.com/ws/location/v1/ip?ip="+ip+"&key="+key;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (Map.Entry entry : map.entrySet()) {
//                System.out.println(key + "--->" + entry);
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String getProvinceList(String id) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "";
            if(StringUtils.isEmpty(id)) {
                urlNameString = "https://apis.map.qq.com/ws/district/v1/getchildren?key="+KEY;
            } else {
                urlNameString = "https://apis.map.qq.com/ws/district/v1/getchildren?key="+KEY + "&id=" + id;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (Map.Entry entry : map.entrySet()) {
//                System.out.println(key + "--->" + entry);
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String getSuggestion(QuerySuggestionForm form) {
        String result = "";
        BufferedReader in = null;
        try {
            if(StringUtils.isEmpty(form.getKeyword())) {
                return "";
            }
            String urlNameString = "https://apis.map.qq.com/ws/place/v1/suggestion?key="+KEY + "&policy=1" + "&keyword=" + form.getKeyword();
            if(!StringUtils.isEmpty(form.getRegion())){
                urlNameString += "&region=" + form.getRegion();
            }
            if(!StringUtils.isEmpty(form.getPageSize()) && !StringUtils.isEmpty(form.getPageNum())) {
                urlNameString += "&page_index=" + form.getPageNum() + "&page_size=" + form.getPageSize();
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (Map.Entry entry : map.entrySet()) {
//                System.out.println(key + "--->" + entry);
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送POST出现异常", e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String getAreaCode(String accNbr, String systemId) {
        String result = "";
        BufferedReader in = null;
        OutputStream out=null;
        try {
            String urlNameString = "http://137.32.173.113:9080/eop/hnsc/AreaCodeQuery/AreaCodeQuery";
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置请求类型为POST
            connection.setRequestMethod("POST");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type","application/json");
            // 设置X-APP-ID与X-APP-KEY
            connection.setRequestProperty("X-APP-ID", "1ea267f4863a84e096c44b904d0b2fcb");
            connection.setRequestProperty("X-APP-KEY", "fa6e7384b5dda595a26b2f5e1c5abcad");
            // 打开输入与输出
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 建立实际的连接
            connection.connect();
            // 整合参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accNbr", accNbr);
            jsonObject.put("systemId", systemId);
            // 传递参数  流的方式
            out=connection.getOutputStream();
            out.write(jsonObject.toJSONString().getBytes());
            out.flush();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
