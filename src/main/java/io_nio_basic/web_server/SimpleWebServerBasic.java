package io_nio_basic.web_server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServerBasic {

    public static void main(String[] args) {
        try (
                ServerSocket server = new ServerSocket(80);
                Socket socket = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ){
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
