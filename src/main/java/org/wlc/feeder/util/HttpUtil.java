package org.wlc.feeder.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

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

    public static String post(String url, String message) throws URISyntaxException, IOException {

        URIBuilder uriBuilder = new URIBuilder(url);
        URI uri = uriBuilder.build();

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        StringEntity input = new StringEntity(message, StandardCharsets.UTF_8);
        httpPost.setEntity(input);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        String responseBody = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();

        return responseBody;
    }
}
