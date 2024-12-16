package server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TsServer {
    // 创建线程池
    private ExecutorService exec = new ThreadPoolExecutor(
            3,
            5,
            100,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(3)
    );

    private int port = 6666;
    private String name = "User";
    private int Number = 0;
//    static ArrayList<InetAddress> clientIpArray = new ArrayList<>();
//    static ArrayList<ObjectOutputStream> bosArray = new ArrayList<>();
    // 用Map更好
    static Map<InetAddress, ObjectOutputStream> bosmap = new HashMap<>();

    public void server() {
        try(
                ServerSocket serverSocket = new ServerSocket(port);
                ) {
            while (true) {
                Socket socket = serverSocket.accept();
//                System.out.println(socket.getInetAddress());
//                clientIpArray.add(socket.getInetAddress());
//                // 每一次有新的客户端接入，我们就获取他的InetAddress
//                bosArray.add(new ObjectOutputStream(socket.getOutputStream()));
//                // 每一次有新的客户端接入，就要创建一个对应的输出流
               bosmap.put(socket.getInetAddress(), new ObjectOutputStream(socket.getOutputStream()));

               exec.execute(new TsSingleServer(socket, "User" + Number));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
