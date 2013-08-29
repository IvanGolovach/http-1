package net.golovach.junior.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

public class Server30 {
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = new ThreadPoolExecutor(
                4, 64,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(256));
        ServerSocket serverSocket = new ServerSocket(80);

        while (true) {
            final Socket socket = serverSocket.accept();
            threadPool.submit(new HttpHandler(socket));
        }
    }
}
