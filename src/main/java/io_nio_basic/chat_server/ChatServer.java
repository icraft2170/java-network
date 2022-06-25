package io_nio_basic.chat_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServer {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(10001);
            System.out.println("접속을 기다립니다.");
            HashMap hm = new HashMap<>();
            while (true) {
                Socket socket = server.accept();
                ChatThread chatThread = new ChatThread(socket, hm);
                chatThread.start();
            }
        } catch(Exception e) {
            System.out.println(e);
        }

    }
}
