package com.demo.concurrent.threadpool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author brandon
 * Created on 2019-09-01.
 * desc:
 **/
public class SimpleHttpServer {


    public static void main(String[] args) throws Exception {
        String basePath = "E:\\workspace\\java\\brandon\\demo\\src\\main\\java\\com\\demo\\concurrent\\threadpool\\resources";
        SimpleHttpServer.setPort(8080);
        SimpleHttpServer.setBasePath(basePath);
        SimpleHttpServer.start();
    }

    //处理HTTP请求的线程池
    private static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>();

    //simpleHttpServer的根路径
    private static String basePath;

    private static ServerSocket serverSocket;

    //服务监听的端口
    private static int port = 8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        System.out.println("http server start...");
        while ((socket = serverSocket.accept()) != null) {
            // 接受一个客户端socket，生成一个httpRequestHandler,放入线程池执行
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    private static void close(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (Exception ex) {
                }
            }
        }
    }


    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 计算出绝对路径
                String filePath = basePath +

                        header.split(" ")[1];
                System.out.println("header --> " + header + " filePath --> " + filePath);
                out = new PrintWriter(socket.getOutputStream());
                // 如果请求资源为jpg，ico等图片资源，则读取资源并输出
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new
                            FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
                if (out != null) {
                    out.println("HTTP/1.1 500");
                    out.println(ex.getMessage());
                    out.flush();
                }
            } finally {
                close(br, in, reader, out, socket);
            }
        }
    }
}
