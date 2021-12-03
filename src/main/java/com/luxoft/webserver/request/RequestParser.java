package com.luxoft.webserver.request;

import com.luxoft.webserver.exception.ResponseCode;
import com.luxoft.webserver.exception.ServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {


    public Request parse(BufferedReader socketReader) throws IOException {
        Request request = new Request();
        String firstLine  = socketReader.readLine();
        injectUriAndMethod(firstLine, request);

        injectHeaders(socketReader, request);

        return request;
    }

    private static void injectUriAndMethod(String line, Request request) {
        if (line == null) {
            throw new ServerException(ResponseCode.BAD_REQUEST);
        }

        String[] splitLine = line.split("\\s");

        if (!splitLine[0].equals(HttpMethod.GET.toString())) {
            throw new ServerException(ResponseCode.METHOD_NOT_ALLOWED);
        }

        request.setMethod(HttpMethod.valueOf(splitLine[0].trim()));
        request.setUri(splitLine[1].trim());
        request.setVersion(splitLine[2].trim());

    }

    private static void injectHeaders(BufferedReader reader, Request request) throws IOException {
        Map<String , String > headers = new HashMap<>();
        while (true){
            String line = reader.readLine();
            if (line.equals("")) {
                break;
            }
            String[] elements = line.split(": ");
            headers.put(elements[0], elements[1]);
        }
        request.setHeaders(headers);
    }

}
