package org.wlc.feeder.constant;

import com.google.gson.Gson;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午6:56
 */
public class GsonSingleton {
    private static Gson instance;

    private GsonSingleton() {
        // 私有构造函数，防止外部实例化
    }

    public static Gson getInstance() {
        if (instance == null) {
            synchronized (GsonSingleton.class) {
                if (instance == null) {
                    instance = new Gson();
                }
            }
        }
        return instance;
    }
}
