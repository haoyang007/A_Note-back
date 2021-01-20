package com.hhhao.note.util;

/**
 * 设置Cron表达式
 * 
 * @author haoy
 *
 */
public class CronUtil {
    /**
     * 将时间字符串转化成Cron表达式
     * 
     * @param dateStr MMdd格式
     * @return
     */
    public String toCron(String dateStr) {
        return "0 0/2 10 " + dateStr.substring(2) + " " + dateStr.substring(0, 2) + " ? *";

    }
}
