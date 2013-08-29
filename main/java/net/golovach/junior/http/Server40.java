package net.golovach.junior.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server40 {
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = new ThreadPoolExecutor(
                4, 64,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(256));
        ServerSocket serverSocket = new ServerSocket(80);

        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.submit(new JSandCSSHttpHandler(socket));
        }
    }
}
