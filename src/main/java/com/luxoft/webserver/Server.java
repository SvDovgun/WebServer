package com.luxoft.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(3000);
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            while (true) {
                String messageFromClient = bufferedReader.readLine();
                System.out.println(messageFromClient);
                //parse message
                if (messageFromClient.equals("")) {
                    break;
                }
            }


            System.out.println("Request obtained");
            //answer
            bufferedWriter.write("HTTP/1.1 200 OK");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write("Hello Chrome!");
        }

    }
}
