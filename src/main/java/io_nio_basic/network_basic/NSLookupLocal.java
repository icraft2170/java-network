package io_nio_basic.network_basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookupLocal {

    public static void main(String[] args) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());

        System.out.println("byte[] 형식의 ip주소 출력");
        byte[] ip = inetAddress.getAddress();
        for (int i = 0; i < ip.length; i++) {
            System.out.print((int)ip[i]);
            if (i != ip.length - 1) {
                System.out.print(".");
            }
        }
    }
}
