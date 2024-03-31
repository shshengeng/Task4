package com.example.common.utils;

public class StringUtil {

    // 判断字符串是否为空
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // 判断字符串是否不为空
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    // 去除字符串两端的空白字符
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    // 判断两个字符串是否相等，忽略大小写
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    // 字符串连接
    public static String concat(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    // 字符串截取
    public static String substring(String str, int beginIndex, int endIndex) {
        if (str == null) {
            return null;
        }
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (endIndex > str.length()) {
            endIndex = str.length();
        }
        return str.substring(beginIndex, endIndex);
    }

    // 字符串格式化
    public static String format(String template, Object... args) {
        return String.format(template, args);
    }
}
