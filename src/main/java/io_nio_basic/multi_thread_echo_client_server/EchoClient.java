package io_nio_basic.multi_thread_echo_client_server;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try(
                Socket socket = new Socket("127.0.0.1", 10001);
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
            ) {
            String line = null;
            while ((line = keyboard.readLine()) != null) {
                if (line.equals("quit")) break;
               pw.println(line);
               pw.flush();
                String response = br.readLine();
                System.out.println("서버로부터 전달받은 문자열 : " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
