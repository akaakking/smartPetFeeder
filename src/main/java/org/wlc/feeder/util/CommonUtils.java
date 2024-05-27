package org.wlc.feeder.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/24 下午2:55
 */
public class CommonUtils {
    public static String getyyyymmddHHmmss() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return now.format(formatter);
    }
}
