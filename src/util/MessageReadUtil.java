package util;

import javabean.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MessageReadUtil {
    public static ArrayList<Message> messagesRead() {
        ArrayList<Message> messages = new ArrayList<>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("message"));) {
            messages = (ArrayList<Message>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return messages;
    }

    public static void main(String[] args) {
        ArrayList<Message> messages = messagesRead();
        for (Message message : messages) {
            System.out.println(message);
        }
    }
}
