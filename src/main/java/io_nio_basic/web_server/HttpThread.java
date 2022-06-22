package io_nio_basic.web_server;

import java.io.*;
import java.net.Socket;
import java.net.URI;

public class HttpThread extends Thread{
    private final Socket socket;
    BufferedReader br = null;
    PrintWriter pw = null;

    public HttpThread(Socket socket) {
        this.socket = socket;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        BufferedReader fbr = null;
        try {
            String line = br.readLine();
            int start = line.indexOf(" ") + 2;
            int end = line.lastIndexOf("HTTP") - 1;
            String filename = line.substring(start, end);
            if (filename.equals("")) {
                filename = "index.html";
            }
            System.out.println("사용자가 " + filename + "을 요청했습니다.");

            fbr = new BufferedReader(new FileReader("/Users/sonhyeonho/study/java-network/src/main/java/io_nio_basic/web_server/html/" + filename));
            String fline = null;
            while ((fline = fbr.readLine()) != null) {
                pw.println(fline);
                pw.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (fbr != null) fbr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
            try {
                if(socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
