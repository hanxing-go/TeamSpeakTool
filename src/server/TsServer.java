package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

    public void server() {
        try(
                ServerSocket serverSocket = new ServerSocket(port);
                ) {
            while (true) {
                Socket socket = serverSocket.accept();
                exec.execute(new TsSingleServer(socket, "User" + Number));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
