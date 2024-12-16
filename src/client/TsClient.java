package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TsClient {
    public static boolean connectflag;
    /*
            1. 键盘输入服务端的IP地址，这样才可以连接到对应的群组
            2. 设置用户名
            3. 向客户端发送信息
            4. 接收到其他用户发送的信息
            */
    private String username = null;
    private String ipaddress = null;
    private int port = 6666;
    private Socket socket = null;
    public void putAndGetMessage() throws IOException {
        // 发送信息和获取信息应该要两个单独的线程
        try{
            Socket socket = new Socket(ipaddress, port);
            // 连接标志打开
            connectflag = true;
            new PutMessageThread(socket, username).start();// 匿名类，直接启动
            new GetMessageThread(socket).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void init() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        username = sc.nextLine();
        System.out.println("请输入群组域名");
        ipaddress = sc.nextLine();

        // 建立连接 发送和接受消息
        putAndGetMessage();
    }
    public static void main(String[] args) throws IOException {
        new TsClient().init();
    }
}
