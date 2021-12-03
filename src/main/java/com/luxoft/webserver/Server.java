package com.luxoft.webserver;

import com.luxoft.webserver.request.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int port ;
    private String webAppPath;

    public void start() throws Exception{
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter);
                requestHandler.setWebAppPath(webAppPath);
                requestHandler.handle();

            }
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void serWebAppPath(String webAppServer) {
        this.webAppPath = webAppServer;
    }


}
