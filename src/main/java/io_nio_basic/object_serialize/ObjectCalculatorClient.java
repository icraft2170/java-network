package io_nio_basic.object_serialize;

import java.io.*;
import java.net.Socket;

public class ObjectCalculatorClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(0);
        }
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            socket = new Socket(args[0], 10005);

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            BufferedReader keyboard =
                    new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            while (true) {
                System.out.println("첫 번째 숫자를 입력해주세요!.");
                line = keyboard.readLine();
                int op1 = 0;
                try {
                    op1 = Integer.parseInt(line);
                } catch (NumberFormatException exception) {
                    op1 = 0;
                }
                System.out.println("두 번째 숫자를 입력해주세요!.");
                line = keyboard.readLine();
                int op2 = 0;
                try {
                    op2 = Integer.parseInt(line);
                } catch (NumberFormatException exception) {
                    op2 = 0;
                }
                System.out.println("사칙연산 기호를 입력하세요");

                line = keyboard.readLine();
                String opCode = "+";
                if (line.equals("+")||line.equals("-")||line.equals("*")||line.equals("/"))
                    opCode = line;
                else
                    opCode = "+";

                SendData sd = new SendData(op1, op2, opCode);
                oos.writeObject(sd);
                oos.flush();
                String msg = (String) ois.readObject();
                System.out.println(msg);
                System.out.println("계속 계산하시겠습니까?(Y/N)");
                line = keyboard.readLine();
                if (line.equals("n")) break;
                System.out.println("다시 계산을 시작합니다.");
            }
            System.out.println("프로그램을 종료합니다.");
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
                try {
                    if (oos != null) oos.close();
                } catch (IOException e) {
                }
                try {
                    if (ois != null) ois.close();
                } catch (IOException e) {
                }
                try {
                    if (socket != null) socket.close();
                } catch (IOException e) {
                }
            }
        }
    }

