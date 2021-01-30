package top.shaozuo.geektime.java01.week03.gateway02;

import java.util.Arrays;

import top.shaozuo.geektime.java01.week03.gateway02.inbound.HttpInboundServer;

public class NettyServerApplication {

    public static final String GATEWAY_NAME = "NIOGateway";
    public static final String GATEWAY_VERSION = "2.0.0";

    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort", "8888");
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8803");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port,
                Arrays.asList(proxyServer.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:"
                + port + " for server:" + server.toString());
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
