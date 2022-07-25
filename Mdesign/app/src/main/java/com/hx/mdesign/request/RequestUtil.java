package com.hx.mdesign.request;

import static com.hx.mdesign.utils.Conversion.MD5;

/**
 * @author: Hx
 * @date: 2022年03月08日 18:42
 */
public final class RequestUtil {

    public static final String TEXT_URL = "https://aiapi.jd.com/jdai/garbageTextSearch";
    public static final String IMAGE_URL = "https://aiapi.jd.com/jdai/garbageImageSearch";

    public static final String SECRET_KEY = "57737ccaa4423e520294da3ab4483a99";
    public static final String APP_KEY = "69666e1c2057795ff83c35839b853571";


    /**
     * 获取url
     *
     * @param url
     * @return
     */
    public static String getUrl(String url) {

        long time = System.currentTimeMillis();
        return url + "?appkey=" + APP_KEY +
                "&timestamp=" + time +
                "&sign=" + MD5(SECRET_KEY + time);
    }
}
