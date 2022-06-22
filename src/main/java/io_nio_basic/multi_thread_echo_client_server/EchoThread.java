package io_nio_basic.multi_thread_echo_client_server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class EchoThread extends Thread{
    private final Socket socket;

    public EchoThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InetAddress inetAddress = socket.getInetAddress();
        System.out.println(inetAddress.getHostAddress() + " 로 부터 접속했습니다.");

        try (
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                PrintWriter pr = new PrintWriter(out);
        ){
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("클라이언트로부터 전송받은 문자열 : " + line);
                pr.println(line);
                pr.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
