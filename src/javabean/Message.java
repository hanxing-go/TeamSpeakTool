package javabean;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

// 这个对象是要可序列化的
public class Message implements Serializable {
    private static final long serialVersionUID = -4593705275313035061L;
    // 1. 首先要记录这条消息接收到的时间
    // 肯定是以服务器接收的时间为准，这样也更简单
    private Date date;
    // 2. 记录这条消息的内容
    private String message;
    // 3. 记录这条发送这条消息的用户
    private String user;


    public Message() {
    }

    public Message(Date data, String message, String user) {
        this.date = data;
        this.message = message;
        this.user = user;
    }

    /**
     * 获取
     * @return date
     */
    public Date getData() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setData(Date date) {
        this.date = date;
    }

    /**
     * 获取
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    public String toString() {
        return "Message{data = " + date + ", message = " + message + ", user = " + user + "}";
    }
}
