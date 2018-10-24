package io;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //创建一个ServerSock监听8080端口
            ServerSocket server = new ServerSocket(2000);
            Socket socket = server.accept();
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = is.readLine();
            System.out.println("received from client: " + s);
            //创建printWriter,用于发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("received data: " + s);
            pw.flush();
            pw.close();
            is.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
