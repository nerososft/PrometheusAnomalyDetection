package com.neroyang.anomalydetection.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:43
 */
public class ConfigUtil {

    private Map<String, String> config;

    public ConfigUtil() throws IOException {
        config = configToMap();
    }

    public String getConfig(String key){
        if(!config.isEmpty() && config.containsKey(key)){
            return config.get(key);
        }
        return "";
    }

    private Map<String, String> configToMap() throws IOException {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        String savePath;savePath = servletContext.getRealPath("/WEB-INF/classes/config/config.properties");

        Map<String, String> configMap = new HashMap<String, String>();
        InputStream inputStream =null;
        InputStreamReader inputStreamReader =null;
        BufferedReader bufferedReader =null;
        try {
            inputStream = new FileInputStream(savePath);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            String[] lines;
            while ((line = bufferedReader.readLine()) != null) {

                if ("".equals(line)) continue;
                if (line.startsWith("#")) continue;

                line = line.trim();
                lines = line.split("=");
                configMap.put(lines[0], lines[1]);
            }
            return configMap;
        }finally {
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }
    }
}
