package cn.net.sybt.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateToString { //时间工具类
    public static String parseTime(Date date) {
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 设置北京时区
        return bjSdf.format(date);
    }
}
