package com.cubecrusher.warthgame.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/** Launches the server application. */
public class ServerLauncher {

	static int portNumber = 4444;
	private static ServerSocket serverSocket;
	protected static ArrayList<ClientThread> clients;
	public static void main(String[] args) {
		// TODO Implement server application.

		serverSocket = null;
		System.out.println("Input your username.");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		sc.close();

		try {
			System.out.println("Started listening on port "+portNumber);
			serverSocket = new ServerSocket(portNumber);
			acceptClients();
		} catch (IOException e) {
			System.out.println("Failed to listen on port "+portNumber);
			System.exit(1);
		}

	}

	public static void acceptClients(){
		clients = new ArrayList<ClientThread>();
		while(true){
			try {
				Socket socket = serverSocket.accept();
				ClientThread client = new ClientThread(socket);
				Thread thread = new Thread(client);
				thread.start();
				clients.add(client);
			} catch (IOException e){
				System.out.println("Failed to accept client at "+portNumber);
			}
		}
	}
}