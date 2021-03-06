package com.luxoft.webserver;

public class Starter {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.setPort(3000);
        server.serWebAppPath("src/main/resources/webapp");
        server.start();
    }
}

// GET index.html
// response - > "src/main/resources/webapp" +index.html
// http://localhost:3000/index.html

// GET css/style.css
// response - > "src/main/resources/webapp" +css/style.css

// GET uri
// response -> webAppAppPath + uri