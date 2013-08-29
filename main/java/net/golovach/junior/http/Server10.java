package net.golovach.junior.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server10 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(81);
        while (true) {
            System.out.println("wait ...");
            Socket socket = serverSocket.accept();
            System.out.println("get one!");
            try (InputStream in = socket.getInputStream();
                 OutputStream out = socket.getOutputStream()) {
                // READ request
                byte[] request = HttpUtils.readFullRequest(in);
                System.out.println(new String(request, "ISO-8859-1"));
                // WRITE response
                byte[] response = new Date().toString().getBytes("ISO-8859-1");
                out.write(response);
            } finally {
                socket.close();
            }
        }
    }
}
