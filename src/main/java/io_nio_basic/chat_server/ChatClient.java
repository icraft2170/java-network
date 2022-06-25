package io_nio_basic.chat_server;

import java.io.*;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.exit(0);
        }

        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        boolean endFlag = false;
        try {
            socket = new Socket(args[1], 10001);
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            pw.println(args[0]);
            pw.flush();

            // InputThread 는 다른 클라이언트가 적은 글을 받아서 화면에 출력하는 역할을 한다
            InputThread it = new InputThread(socket, br);
            it.start();
            String line = null;
            while ((line = keyboard.readLine()) != null) {
                pw.println(line);
                pw.flush();
                if (line.equals("/quit")) {
                    endFlag = true;
                    break;
                }
            }
            System.out.println("클라이언트의 접속을 종료합니다.");
        } catch (Exception e) {
            if (!endFlag)
            System.out.println(e);
        } finally {
            if (pw != null) pw.close();
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
