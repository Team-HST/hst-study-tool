package com.hst.studytool.utils;

import java.util.Map;

public class StringTemplateUtils {

    public static String template(String template, Map<String, Object> params) {
        String result = template;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            String value = entry.getValue().toString();
            result = result.replace(key, value);
        }
        return result;
    }

}
