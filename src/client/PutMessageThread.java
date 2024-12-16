package client;

import javabean.Message;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class PutMessageThread extends Thread{
    private Socket socket = null;
    private String user;

    public PutMessageThread(Socket socket, String user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() {
        try(
                ObjectOutputStream bos = new ObjectOutputStream(socket.getOutputStream());
           ) {
            // 发送信息
            Scanner sc = new Scanner(System.in);
            System.out.println("在这里发送信息，输入Crtl + D或者 Ctrl + Z退出输入");
            while (sc.hasNext()) {
                String message = sc.nextLine();
                if (message.isEmpty()) {
                    break;
                }

                Message msg = new Message(new Date(), message, user);
                bos.writeObject(msg);
                bos.flush();
            }

            // 要发送一个结束标志
            bos.writeObject(null);
            // 关闭输出流
            socket.shutdownOutput();
            // 断开连接
            TsClient.connectflag = false;
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
