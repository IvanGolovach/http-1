package net.golovach.junior.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server20 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true) {
            Socket socket = serverSocket.accept();
            try (InputStream in = socket.getInputStream();
                 OutputStream out = socket.getOutputStream()) {
                // READ request
                byte[] request = HttpUtils.readFullRequest(in);
                System.out.println(new String(request, "ISO-8859-1"));
                // WRITE response
                byte[] response = HttpUtils.createResponse(new Date().toString());
                out.write(response);
            } finally {
                socket.close();
            }
        }
    }
}
