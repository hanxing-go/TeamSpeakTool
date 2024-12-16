package test;

import javabean.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SendTest {
    public static void main(String[] args) {
        // 写一个简单的客户端
        try (
                Socket socket = new Socket("hanxing", 6666);
                // 创建一个输出流，输出到端口然后发送
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
//                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ) {

            Scanner sc = new Scanner(System.in);

            int cnt = 0;
            while (cnt != 1) {
                String message = sc.nextLine();
                bos.write(message.getBytes());
                bos.flush();
                cnt++;
            }

            socket.shutdownOutput();
            System.out.println("发送成功");

            //接收客户端返回的消息
//            int len = 0;
//            byte[] bytes = new byte[1024];
//            while ((len = bis.read(bytes)) != -1) {
//                System.out.println(new String(bytes, 0, len));
//            }
            Message msg = (Message) ois.readObject();
            if (msg.getUser().equals("test")) {
                System.out.println(msg);
            }

            ois.close();

            System.out.println("消息发送完毕");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
