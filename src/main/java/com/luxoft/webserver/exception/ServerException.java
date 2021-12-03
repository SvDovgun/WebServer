package com.luxoft.webserver.exception;

public class ServerException extends RuntimeException{
    private ResponseCode type;

    public ServerException(ResponseCode type) {
        this.type = type;
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseCode getType() {
        return type;
    }

    public void setType(ResponseCode type) {
        this.type = type;
    }
}
