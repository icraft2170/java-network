package io_nio_basic.network_basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(0);
        }

        InetAddress inetAddr[] = null;
        try {
            inetAddr = InetAddress.getAllByName(args[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < inetAddr.length; i++) {
            System.out.println(inetAddr[i].getHostName());
            System.out.println(inetAddr[i].getHostAddress());
            System.out.println(inetAddr[i].toString());
            System.out.println("--------------------------");
        }
    }
}
