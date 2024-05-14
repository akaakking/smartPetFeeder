package org.wlc.feeder.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午6:41
 */
public class HttpUtil {

    public static String get(String url) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        URI uri = uriBuilder.build();
        HttpGet httpGet = new HttpGet(uri);

        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);

        String responseBody = EntityUtils.toString(response.getEntity());
        response.close();
        closeableHttpClient.close();

        return responseBody;
    }
}
