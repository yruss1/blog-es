package com.xu.blog.task;

import com.xu.blog.common.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.xu.blog.task.tool.*;

@Slf4j
public class PayoutSign {

    public static final String KEY = _51key;
    public static Map<String, Object> signMap = new HashMap<>(16);
    static {

        signMap.put("mchOrderNo", "202205101" + (int)((Math.random()*9+1)*100000000));
        signMap.put("mchNo", "M1714027497");
        signMap.put("appId", _51);
        signMap.put("amount","10000");
        signMap.put("currency", "INR");
        signMap.put("entryType", "IMPS");
        signMap.put("accountNo","HDFC0000247");
        signMap.put("accountCode","HDFC0000247");
        signMap.put("notifyUrl", "https://www.google.com");
        signMap.put("accountName","WANG JUN");
        signMap.put("accountEmail","1243149587@qq.com");
        signMap.put("accountPhone","9811837378");
        signMap.put("reqTime", "" + System.currentTimeMillis());

    }
    public static Map<String, Object> getSign(Map<String, Object> map, String key) {
        List<String> list = new ArrayList<>();
        map = sortMapByKey(map);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null != entry.getValue() && !"".equals(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; ++i) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result = result + "key=" + key;
        result = Objects.requireNonNull(md5(result, "UTF-8")).toUpperCase();
        map.put("sign", result);
        return map;
    }

    public static String md5(String value, String charset) {
        MessageDigest md;
        try {
            byte[] data = value.getBytes(charset);
            md = MessageDigest.getInstance("MD5");
            byte[] digestData = md.digest(data);
            return toHex(digestData);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String toHex(byte[] input) {
        if (input == null) {
            return null;
        } else {
            StringBuilder output = new StringBuilder(input.length * 2);
            for (byte b : input) {
                int current = b & 255;
                if (current < 16) {
                    output.append("0");
                }
                output.append(Integer.toString(current, 16));
            }
            return output.toString();
        }
    }

    public static void main(String[] args) {
        signMap = getSign(signMap, KEY);
        log.info("{}", GsonUtil.toJsonString(signMap));
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object>map) {
        Map<String, Object> treemap = new TreeMap<>(map);
        List<Map.Entry<String, Object>> list = new ArrayList<>(treemap.entrySet());
        list.sort(Map.Entry.comparingByKey());
        return treemap;
    }

}
