package com.hl.blog.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成纯数字
 */
public class RandomUtil {

    public static synchronized String generateUniqueKey() {
        Random random = new Random();
        Integer r = random.nextInt(900000000) + 100000000;
        Long timeMillis = System.currentTimeMillis();
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String timeStr = sdf.format(new Date());
//        return  timeMillis + String.valueOf(r); // 13位毫秒+9位随机数
        return timeStr + r; // 10位时间+9位随机数
    }
}