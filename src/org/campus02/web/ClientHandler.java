package org.campus02.web;

import org.campus02.exception.UrlLoaderException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandler implements Runnable{

    private Socket client;
    private WebProxy proxy;

    public ClientHandler(Socket client, WebProxy proxy) {
        this.client = client;
        this.proxy = proxy;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            String line;
            while ((line = br.readLine()) != null) {
                String [] cmds = line.split(" ");
                if (line.equalsIgnoreCase("bye")) {
                    client.close();
                } else if (cmds.length != 2){
                    bw.write("Error: command invalid");
                    bw.newLine();
                    bw.flush();
                    continue;
                } else if (cmds[0].equalsIgnoreCase("fetch")) {
                    WebPage webPage;
                    try {
                        webPage = proxy.fetch(cmds[1]);
                        bw.write(webPage.getContent());
                    } catch (UrlLoaderException e) {
                        bw.write("Error: loading the url failed");
                    }
                    bw.newLine();
                    bw.flush();
                } else if (cmds[0].equalsIgnoreCase("stats")) {
                    if (cmds[1].equalsIgnoreCase("hits")) {
                        String hits = proxy.statsHits();
                        bw.write(hits);
                    } else if (cmds[1].equalsIgnoreCase("misses")) {
                        String misses = proxy.statsMisses();
                        bw.write(misses);
                    } else {
                        bw.write("Error: invalid command");
                    }
                    bw.newLine();
                    bw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
