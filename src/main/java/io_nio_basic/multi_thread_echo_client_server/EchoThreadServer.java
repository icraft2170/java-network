package io_nio_basic.multi_thread_echo_client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoThreadServer {

    public static void main(String[] args) {

        ServerSocket server = null;
        try{
            server = new ServerSocket(10001);
            System.out.println(" 접속을 기다립니다. ");
            while (true) {
                Socket socket = server.accept();
                EchoThread echoThread = new EchoThread(socket);
                echoThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
