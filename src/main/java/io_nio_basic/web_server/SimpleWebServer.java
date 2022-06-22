package io_nio_basic.web_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(80);
            while (true) {
                System.out.println("접속을 대기 합니다.");
                Socket socket = server.accept();
                HttpThread ht = new HttpThread(socket);
                ht.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
