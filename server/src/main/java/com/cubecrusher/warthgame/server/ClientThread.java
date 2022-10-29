package com.cubecrusher.warthgame.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends ServerLauncher implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(!socket.isClosed()) {
                String input = in.readLine();
                if (input != null) {
                    for (ClientThread client : ServerLauncher.clients) {
                        client.getWriter().write(input);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public PrintWriter getWriter(){
        return out;
    }
}
