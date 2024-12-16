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
import java.util.function.BiConsumer;

public class TsSingleServer implements Runnable{
    /*
    3. 接收来自各个IP地址的数据// 先写单个的，多个的要用线程池
    */

    private Socket socket;
    private InetAddress clientIp;
    // 客户端的Ip
    public TsSingleServer(Socket socket, String name) {
        this.socket = socket;
        clientIp = socket.getInetAddress();

        System.out.println(clientIp);
    }
    public void server() {
        try(
                // 创建输入流，接收端口的输入
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
//                // 创建一个输出流，返回客户端相应信息          这里修改了，只需要获取TsServer里面创建输出流就可以。
//                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
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
                // 将获取的聊天，写入到聊天记录里面
                String getmessage = new String(bytes, 0, len);
                Message message = new Message(new Date(), getmessage, "test");
                messages.add(message);

                // 将当前客户端的每一个输入，发送给所有连接的客户端
//                for (ObjectOutputStream oos : TsServer.bosArray) {
//                    oos.writeObject(message);
//                    oos.flush();
//                    // 将当前获取的直接返回回去了
//                }
                TsServer.bosmap.forEach(new BiConsumer<InetAddress, ObjectOutputStream>() {
                    @Override
                    public void accept(InetAddress inetAddress, ObjectOutputStream objectOutputStream) {
                        try {
                            objectOutputStream.writeObject(message);
                            objectOutputStream.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

            ObjectOutputStream messageBis = new ObjectOutputStream(new FileOutputStream("message")) ;

            // 把新的聊天记录写到文件里面
            messageBis.writeObject(messages);
            // 把发送的聊天，同时也要传送给其他客户端


//            4. 接收成功要返回给客户端
//            bos.write("服务器接收成功".getBytes());
//            bos.flush();

        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                // 要删除当前ip对应的socket和ObjectOutputStream
                TsServer.bosmap.remove(clientIp);
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
