package client;

import javabean.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class GetMessageThread extends Thread{
    private Socket socket;
    public GetMessageThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream bis = new ObjectInputStream(socket.getInputStream());
        ) {
            Message msg;
            while (TsClient.connectflag && !socket.isClosed()) {
                // 除非断开连接，不然要一直接收别人发过来的消息
                msg = (Message) bis.readObject();
                System.out.println(msg);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("通话结束");
        }
    }
}
