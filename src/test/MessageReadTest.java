package test;

import javabean.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MessageReadTest {
    // 读取message
    public static void main(String[] args) {
        ArrayList<Message> messages = new ArrayList<>();

        try (ObjectInputStream ois  = new ObjectInputStream(new FileInputStream("message"))) {

            messages = (ArrayList<Message>) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Message message : messages) {
            System.out.println(message);
        }
    }
}
