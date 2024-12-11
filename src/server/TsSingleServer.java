package server;

import javabean.Message;
import util.MessageReadUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

public class TsSingleServer implements Runnable{
    /*
    3. 接收来自各个IP地址的数据// 先写单个的，多个的要用线程池
    */

    private Socket socket;
    public TsSingleServer(Socket socket, String name) {
        this.socket = socket;
    }
    public void server() {
        try(
                // 创建输入流，接收端口的输入
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                // 创建一个输出流，返回客户端相应信息
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());


                ) {

            boolean appenMode = new File("message").exists();
            // 创建一个聊天记录文件，保存所有聊天信息，注意是追加

            ArrayList<Message> messages = new ArrayList<>();

            // 获取以往文件中所有的聊天记录
            if (appenMode) {
                messages.addAll(MessageReadUtil.messagesRead());
                // 如果是初始记录聊天，就不加
            }
            

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                String getmessage = new String(bytes, 0, len);
                Message message = new Message(new Date(), getmessage, "test");
                messages.add(message);
            }

            ObjectOutputStream messageBis = new ObjectOutputStream(new FileOutputStream("message")) ;

            // 把新的聊天记录写到文件里面
            messageBis.writeObject(messages);

//            4. 接收成功要返回给客户端
            bos.write("服务器接收成功".getBytes());
            bos.flush();

        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        server();
    }
}
