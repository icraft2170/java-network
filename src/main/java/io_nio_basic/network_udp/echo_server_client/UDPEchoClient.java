package io_nio_basic.network_udp.echo_server_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClient {

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
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            dsock = new DatagramSocket();
            String line = null;
            while ((line = br.readLine()) != null) {
                DatagramPacket sendPacket = new DatagramPacket(line.getBytes(), line.getBytes().length, inetAddr, port);
                dsock.send(sendPacket);

                if (line.equals("quit"))
                    break;

                byte[] buffer = new byte[line.getBytes().length];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                dsock.receive(receivePacket);

                String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("전송받은 문자열 : " + msg);
            }
            System.out.println("UDP Echo Client 종료");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (dsock != null) {
                dsock.close();
            }
        }


    }
}
