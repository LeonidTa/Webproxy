package org.campus02.web;

import org.campus02.exception.UrlLoaderException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

            try (ServerSocket serverSocket = new ServerSocket(5678);
                 Socket client = serverSocket.accept()) {

                while (true) {
                    PageCache pageCache = new PageCache();
                    pageCache.warmUp("C:\\Users\\Leo\\Desktop\\JAVA\\SS2022_FH\\fileIn.txt");
                    WebProxy webProxy = new WebProxy(pageCache);

                    ClientHandler clientHandler = new ClientHandler(client, webProxy);
                    clientHandler.run();
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (UrlLoaderException e) {
                e.printStackTrace();
            }

    }
}
