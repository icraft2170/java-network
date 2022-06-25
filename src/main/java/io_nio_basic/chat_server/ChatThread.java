package io_nio_basic.chat_server;

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ChatThread extends Thread{
    private Socket socket;
    private String id;
    private BufferedReader br;
    private HashMap<String, PrintWriter> hm;
    private boolean initFlag = false;
    public ChatThread(Socket socket, HashMap hm) {
        this.socket = socket;
        this.hm = hm;
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); // Client 에게 메시지를 전송하는 역할을 하는 Writer
            br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Client 가 전송한 메시지를 받는 역할을 하는 Reader
            id = br.readLine(); // br을 통해 id를 입력 받음.
            broadcast(id + "님이 접속했습니다.");
            System.out.println("접속한 사용자 아이디는 " + id + "입니다.");
            synchronized (hm) {
                hm.put(this.id, pw);
            }
            initFlag = true;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.equals("/quit")) break;
                if (line.indexOf("/to ") == 0) {
                    sendMsg(line);
                } else {
                    broadcast(id + " : " + line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            synchronized (hm) {
                hm.remove(id);
            }
            broadcast(id + "님이 접속을 종료했습니다.");
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
            }

        }
    }

    public void sendMsg(String msg) {
        int start = msg.indexOf(" ") + 1;
        int end = msg.indexOf(" ", start);

        if (end != -1) {
            String to = msg.substring(start, end);
            String msg2 = msg.substring(end + 1);
            PrintWriter pw = hm.get(to);
            if (pw != null) {
                pw.println(id + "님이 귓속말을 보내셨습니다. " + msg2);
                pw.flush();
            }
        }
    }

    private void broadcast(String msg) {
        synchronized (hm) {
            Collection<PrintWriter> collection = hm.values();
            Iterator<PrintWriter> iter = collection.iterator();
            while (iter.hasNext()) {
                PrintWriter pw = iter.next();
                pw.println(msg);
                pw.flush();
            }
        }
    }
}
