package com.luxoft.webserver.resource;

import com.luxoft.webserver.exception.ResponseCode;
import com.luxoft.webserver.exception.ServerException;

import java.io.*;

public class ResourceReader {
    private String webAppPath;
    public File uriFile;


    public InputStream readResource(String uri) throws FileNotFoundException {
        File uriFile = new File(webAppPath, uri);

        if (!uriFile.exists()) {
            System.out.println("Error in readResource for " + uriFile);
            new ServerException(ResponseCode.NOT_FOUND);
        }
        setUriFile(uriFile);

        FileInputStream fileInputStream = new FileInputStream(uriFile);

        return fileInputStream;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public File getUriFile() {
        return uriFile;
    }

    public void setUriFile(File uriFile) {
        this.uriFile = uriFile;
    }


}
