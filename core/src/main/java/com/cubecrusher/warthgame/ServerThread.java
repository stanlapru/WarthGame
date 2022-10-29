package com.cubecrusher.warthgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;
    private String name;
    private BufferedReader sIn;
    private BufferedReader uIn;
    private PrintWriter out;

    public ServerThread(Socket socket, String name){
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            uIn = new BufferedReader(new InputStreamReader(System.in));

            while (!socket.isClosed()){
                if (sIn.ready()){
                    String input = sIn.readLine();
                    if (input != null){
                        System.out.println(input);
                    }
                }
                if (uIn.ready()){
                    out.println(name+": "+uIn.readLine());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
