package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class IPTest {
    public static void main(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address);

        System.out.println(new Date());
    }
}
