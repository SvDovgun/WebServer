package com.luxoft.webserver.response;

import com.luxoft.webserver.exception.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;


public class ResponseWriter {

    public void  writeSuccessResponse(InputStream inputStream, BufferedWriter socketWriter) throws IOException {
        try {
            String responseText = "HTTP/1.1 " + ResponseCode.OK.getCode() + " " + ResponseCode.OK.getCodeName();
            socketWriter.write(responseText );
            socketWriter.newLine();
            socketWriter.newLine();
        //    socketWriter.write("Hello from Server! at " + new Timestamp(System.currentTimeMillis()));

            byte[] buffer = new byte[1024];

            while (( inputStream.read(buffer)) > 0 ) {
                String s = new String(buffer, StandardCharsets.UTF_8);
                socketWriter.write(s);
                System.out.println(s);
            }

        } catch (IOException e) {
            System.out.println("Error in writeSuccessResponse");
            writeErrorResponse(socketWriter, new com.luxoft.webserver.exception.ServerException(ResponseCode.INTERNAL_SERVER_ERROR));

        }

    }

    public void writeErrorResponse(BufferedWriter writer, com.luxoft.webserver.exception.ServerException exception) throws IOException {
        String errorText = ("HTTP/1.1 " + exception.getType().getCode()+ " " + exception.getType().getCodeName());
        writer.write(errorText);
        writer.newLine();
        writer.newLine();

    }
}
