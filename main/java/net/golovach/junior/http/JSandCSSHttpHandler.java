package net.golovach.junior.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;

public class JSandCSSHttpHandler implements Callable<Void> {
    private final Socket socket;

    public JSandCSSHttpHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Void call() throws IOException {
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
            // READ request
            byte[] request = HttpUtils.readFullRequest(in);
            System.out.println(new String(request, "ISO-8859-1"));
            // WRITE response
            byte[] response = HttpUtils.createJSandCSSResponse(new Date().toString());
            System.out.println(new String(response, "ISO-8859-1"));
            out.write(response);
        } finally {
            socket.close();
        }
        return null;
    }
}
