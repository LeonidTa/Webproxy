package org.campus02.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5678);
             Socket client = serverSocket.accept()){




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
