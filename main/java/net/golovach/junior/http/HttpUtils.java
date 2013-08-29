package net.golovach.junior.http;

import java.io.*;

public class HttpUtils {

    public static boolean isRequestEnd(byte[] request, int length) {
        if (length < 4) {
            return false;
        }
        return request[length - 4] == '\r' &&
                request[length - 3] == '\n' &&
                request[length - 2] == '\r' &&
                request[length - 1] == '\n';
    }

    public static byte[] readFullRequest(InputStream in) throws IOException {
        byte[] buff = new byte[1024];
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        while (true) {
            int count = in.read(buff);
            result.write(buff, 0, count);
            if (isRequestEnd(buff, count)) {
                return result.toByteArray();
            }
        }
    }

    public static byte[] createResponse(String text) throws IOException {
        String html = ""
                + "<html>"
                + "<body>"
                + "<p style=\"color: #0000FF; font-size: 48pt\">"
                + text
                + "</p>"
                + "</body>"
                + "</html>";

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(result, "ISO-8859-1");
        // http header
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html; charset=ISO-8859-1\r\n");
        writer.write("Content-Language: en\r\n");
        writer.write("Content-Length: " + html.length() + "\r\n");
        writer.write("Connection: close\r\n");
        writer.write("\r\n");
        // html
        writer.write(html);
        writer.flush();

        return result.toByteArray();
    }

    public static byte[] createJSandCSSResponse(String text) throws IOException {
        String html = ""
                + "<html>"
                + "<head>"
                + "<style type=\"text/css\">\n" +
                "    .styleA {\n" +
                "        color: blue;\n" +
                "        text-decoration: bold;\n" +
                "        font-size: 48pt;\n" +
                "    }\n" +
                "    .styleB {\n" +
                "        color: gray;\n" +
                "        text-decoration: italic;\n" +
                "        font-size: 36pt;\n" +
                "        font-family:verdana;\n" +
                "        text-align:center;\n" +
                "        background-color:yellow;\n" +
                "    }\n" +
                "    </style>"
                + "<script type=\"text/JavaScript\">\n"
                + "function timedRefresh(timeoutPeriod) {\n"
                + "    setTimeout(\"location.reload(true);\",timeoutPeriod);\n"
                + "}\n"
                + "</script>\n"
                + "</head>\n"
                + "<body onload=\"JavaScript:timedRefresh(1000);\">\n"
                + "<p class=\"styleA\">" + text + "</p>\n"
                + "<p class=\"styleB\">" + text + "</p>\n"
                + "</body>\n"
                + "</html>";

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(result, "ISO-8859-1");
        // http header
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html; charset=ISO-8859-1\r\n");
        writer.write("Content-Language: en\r\n");
        writer.write("Content-Length: " + html.length() + "\r\n");
        writer.write("Connection: close\r\n");
        writer.write("\r\n");
        // html
        writer.write(html);
        writer.flush();

        return result.toByteArray();
    }

    public static byte[] createCometResponse(String text) throws IOException {
        String html = ""
                + "<html>"
                + "<head>"
                + "<script type=\"text/JavaScript\">\n"
                + "function timedRefresh() {\n"
                + "    var xhr = new XMLHttpRequest();\n"
                + "    xhr.onreadystatechange = function() {\n"
                + "        if (xhr.readyState == 4) {\n"
                + "            alert(xhr.responseText);\n"
                + "        }\n"
                + "    }\n"
                + "    xhr.open('GET', 'localhost', true);\n"
                + "    xhr.send(null);\n"
                + "}\n"
                + "</script>\n"
                + "</head>\n"
//                + "<body onload=\"JavaScript:setInterval(timedRefresh(), 1000);\">\n"
                + "<body onload=\"JavaScript:setInterval(function(){alert(\"Hello\")},3000);\">\n"
                + text + "\n"
                + "</body>\n"
                + "</html>";

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(result, "ISO-8859-1");
        // http header
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html; charset=ISO-8859-1\r\n");
        writer.write("Content-Language: en\r\n");
        writer.write("Content-Length: " + html.length() + "\r\n");
        writer.write("Connection: close\r\n");
        writer.write("\r\n");
        // html
        writer.write(html);
        writer.flush();

        return result.toByteArray();
    }
}
