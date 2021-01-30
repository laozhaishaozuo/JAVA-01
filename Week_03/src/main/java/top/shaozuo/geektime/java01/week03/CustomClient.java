package top.shaozuo.geektime.java01.week03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CustomClient {

    private static Logger logger = LoggerFactory.getLogger(CustomClient.class);

    private static final String SERVER_URL = "http://localhost:8808/test";//gateway01
    private static final String SERVER_URL2 = "http://localhost:8888/test";//gateway02
    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws Exception {
        //doGet(SERVER_URL);
        doGet(SERVER_URL2);
    }

    private static void doGet(String url) throws Exception {
        Request request = new Request.Builder().url(url).build();
        logger.info("访问网关：{}", url);
        try (Response response = client.newCall(request).execute()) {
            logger.info("添加的过滤信息:{}", response.header("shaozuo"));
            logger.info("返回信息:{}", response.body().string());
        }
    }
}
