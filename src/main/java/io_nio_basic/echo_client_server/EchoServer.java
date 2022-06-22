package io_nio_basic.echo_client_server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        PrintWriter pw = null;
        BufferedReader br = null;
        Socket socket = null;
        try {
            ServerSocket server = new ServerSocket(10001);
            System.out.println("접속을 기다립니다.");
            socket = server.accept();

            InetAddress inetAddress = socket.getInetAddress();
            System.out.println(inetAddress.getHostAddress() + " 로 부터 접속했습니다.");

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            pw = new PrintWriter(new OutputStreamWriter(out));
            br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("클라이언트로 부터 전송받은 문자열 : " + line);
                pw.println(line);
                pw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
