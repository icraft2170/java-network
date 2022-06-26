package io_nio_basic.network_udp.echo_server_client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(0);
        }
        int port = 0;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("port 번호는 양의 정수로 입력해서 주세요.");
            System.exit(0);
        }
        DatagramSocket dsock = null;
        try {
            System.out.println("접속 대기상태입니다.");
            dsock = new DatagramSocket(port);
            String line = null;
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                dsock.receive(receivePacket);
                String msg = new String(
                        receivePacket.getData(), 0, receivePacket.getLength()
                );
                System.out.println("전송 받은 문자열 : " + msg);
                if (msg.equals("quit"))
                    break;

                DatagramPacket sendPacket = new DatagramPacket(
                        receivePacket.getData(), receivePacket.getData().length,
                        receivePacket.getAddress(), receivePacket.getPort()
                );
                dsock.send(sendPacket);
            }
            System.out.println("UDP Echo Server를 종료합니다.");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (dsock != null) {
                dsock.close();
            }
        }


    }
}
