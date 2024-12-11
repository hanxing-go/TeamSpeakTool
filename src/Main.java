import server.TsServer;

import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {
        // 提供一个选择，设定当前是客户端还是服务端

        // 先把服务端写了
        TsServer tsServer = new TsServer();
        tsServer.server();


    }
}