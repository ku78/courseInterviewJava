package com.excentro.geekbrains.hw6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class SocketHTTPClient {

  public static void main(String[] args) throws IOException {

    URL url = new URL("http://localhost:8080/employee");

    String hostName = url.getHost();
    int port = url.getPort();

    getRequest(url, hostName, port);
    postRequest(url, hostName, port);
    getRequest(url, hostName, port);
  }

  private static void postRequest(URL url, String hostName, int port) throws IOException {
    try (Socket socket = new Socket(hostName, port)) {
      OutputStream out = socket.getOutputStream();
      InputStream in = socket.getInputStream();
      String data = "{\"name\":\"Jane\",\"role\":\"director\"}";

      String postRequest =
          "POST "
              + url.getPath()
              + " HTTP/1.0\r\n"
              + "Accept: */*\r\n"
              + "Host: "
              + hostName
              + "\r\n"
              + "Content-Type: application/json; charset=utf-8\r\n"
              + "Content-Length: "
              + data.length()
              + "\r\n\r\n"
              + data;

      readAndWriteBuffer(out, in, postRequest);
    }
  }

  private static void readAndWriteBuffer(OutputStream out, InputStream in, String postRequest)
      throws IOException {
    out.write(postRequest.getBytes());
    out.flush();

    StringBuilder response = new StringBuilder();
    byte[] buffer = new byte[4096];
    int bytesRead;

    while ((bytesRead = in.read(buffer, 0, 4096)) != -1) {
      for (int i = 0; i < bytesRead; i++) response.append((char) buffer[i]);
    }

    System.out.println(response);
  }

  private static void getRequest(URL url, String hostName, int port) throws IOException {
    try (Socket socket = new Socket(hostName, port)) {
      OutputStream out = socket.getOutputStream();
      InputStream in = socket.getInputStream();

      String getRequest =
          "GET "
              + (url != null ? url.getPath() : "/")
              + " HTTP/1.0\r\n"
              + "Accept: */*\r\n"
              + "Host: "
              + hostName
              + "\r\n"
              + "Connection: Close\r\n\r\n";

      readAndWriteBuffer(out, in, getRequest);
    }
  }
}
