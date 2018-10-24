package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        String msg = "client data";
        try {
            //创建一个socket,跟本机的8080端口连接
            Socket socket = new Socket("127.0.0.1", 2000);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据
            pw.println(msg);
            pw.flush();
            String s = is.readLine();
            System.out.println("received from server: " + s);
            pw.close();
            is.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
