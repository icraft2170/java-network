package io_nio_basic.network_udp.time_server_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTimeClient {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.exit(0);
        }
        String ip = args[0];
        int port = 0;
        try {
            port = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("port 번호는 양의 정수로 입력해서 주세요.");
            System.exit(0);
        }
        InetAddress inetAddr = null;
        try {
            inetAddr = InetAddress.getByName(ip);
        } catch (Exception e) {
            System.out.println(e);
        }

        DatagramSocket dsock = null;
        try {
            dsock = new DatagramSocket();
            String line = null;
            DatagramPacket sendPacket = new DatagramPacket("".getBytes(), "".getBytes().length, inetAddr, port);
            dsock.send(sendPacket);


            byte[] buffer = new byte[200];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            dsock.receive(receivePacket);

            String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("서버로 부터 전달받은 시간 : " + msg.trim());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (dsock != null) {
                dsock.close();
            }
        }


    }
}
