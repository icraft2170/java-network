package io_nio_basic.object_serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectCalculatorServer {

    public static void main(String[] args) {
        Socket sock = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ServerSocket server = new ServerSocket(10005);
            System.out.println("클라이언트의 접속을 대기합니다.");
            sock = server.accept();

            oos = new ObjectOutputStream(sock.getOutputStream());
            ois = new ObjectInputStream(sock.getInputStream());

            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                SendData sd = (SendData) obj;
                int op1 = sd.getOp1();
                int op2 = sd.getOp2();
                String opCode = sd.getOpCode();

                if (opCode.equals("+")) {
                    oos.writeObject(op1 + " + " + op2 + " = " + (op1 + op2));
                    oos.flush();
                } else if (opCode.equals("-")) {
                    oos.writeObject(op1 + " - " + op2 + " = " + (op1 - op2));
                    oos.flush();
                } else if (opCode.equals("*")) {
                    oos.writeObject(op1 + " x " + op2 + " = " + (op1 * op2));
                    oos.flush();
                } else if (opCode.equals("/")) {
                    if (op2 == 0) {
                        oos.writeObject("0으로 나눌 수 없습니다.");
                        oos.flush();
                    } else {
                        oos.writeObject(op1 + " / " + op2 + " = " + (op1 / op2));
                        oos.flush();
                    }
                }
                System.out.println("결과를 전송했습니다.");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if(oos!=null) oos.close();
            } catch (IOException e) {}
            try {
                if(ois!=null)  ois.close();
            } catch (IOException e) {}
            try {
                if(sock!=null) { sock.close();}
            } catch (IOException e) {}
        }
    }
}
