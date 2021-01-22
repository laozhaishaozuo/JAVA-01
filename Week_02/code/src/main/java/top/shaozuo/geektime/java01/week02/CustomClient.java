package top.shaozuo.geektime.java01.week02;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CustomClient {

    private static final String SERVER_URL = "http://localhost:8801";

    public static void main(String[] args) throws Exception {
        doGet(SERVER_URL);
    }

    private static void doGet(String url) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(new HttpGet(url));) {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            String html = EntityUtils.toString(entity);
            System.out.println(html);
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }
}
