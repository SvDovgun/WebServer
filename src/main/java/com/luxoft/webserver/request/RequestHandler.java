package com.luxoft.webserver.request;

import com.luxoft.webserver.exception.ResponseCode;
import com.luxoft.webserver.exception.ServerException;
import com.luxoft.webserver.resource.ResourceReader;
import com.luxoft.webserver.response.ResponseWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class RequestHandler {
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    private String webAppPath;

    public RequestHandler(BufferedReader socketReader, BufferedWriter socketWriter) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
    }


    public void handle() {
        RequestParser parser  = new RequestParser();
        ResponseWriter writer = new ResponseWriter();

        Request request = null;

    try {
        request = parser.parse(socketReader);
        ResourceReader resourceReader = new ResourceReader();
        resourceReader.setWebAppPath(webAppPath);
        writer.writeSuccessResponse(resourceReader.readResource(request.getUri()), socketWriter);

    } catch (Exception e) {
        System.out.println("Error in requestHandler");
        new ServerException(ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }

    public BufferedReader getSocketReader() {
        return socketReader;
    }

    public void setSocketReader(BufferedReader socketReader) {
        this.socketReader = socketReader;
    }

    public BufferedWriter getSocketWriter() {
        return socketWriter;
    }

    public void setSocketWriter(BufferedWriter socketWriter) {
        this.socketWriter = socketWriter;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }
}
