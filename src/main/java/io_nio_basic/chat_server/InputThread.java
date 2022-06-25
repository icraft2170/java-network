package io_nio_basic.chat_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class InputThread extends Thread{
    private Socket socket;
    private BufferedReader br;
    public InputThread(Socket socket, BufferedReader br) {
        this.socket = socket;
        this.br = br;
    }

    @Override
    public void run() {
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
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
