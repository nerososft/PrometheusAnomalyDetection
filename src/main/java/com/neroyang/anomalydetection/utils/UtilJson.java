package com.neroyang.anomalydetection.utils;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2017/8/25
 * Time   下午4:22
 */
public class UtilJson {

    public static Object convertValue(Object val, Class<?> targetClass) {
        return targetClass.cast(val);
    }

    public static String writeValueAsString(Object result) throws IOException {
        Gson gson = new Gson();
        return gson.toJson(result);
    }

    public static Map<String,Object> toMap(String params) throws ParseException {
        return JSON.parse(params,Map.class);
    }

    public static class JSON_MAPPER {
        public static void configure(SerializationFeature writeNullMapValues, boolean b) {

        }
    }
}
