package a2;
/*
 * The Server class keeps listening for client requests as long as the running.txt file exits. 
 * Whenever a request comes and is accepted, it is handled  in a separate thread by the worker class
 *  To start the server, pass in the port number in the command prompt
 * To stop the server, simply force the server to stop using CTRL + C
 */

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public Server(int port, File file, PrintWriter logger) throws Exception
	{
		//Create a new server
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(port);
        //Keep the server open as long as the running file in the  exits
		while (true)
		{
			//Open a socket connection and let the worker class handle it in a seprate thread
 			Socket client = server.accept();
	 		Worker worker = new Worker(client, logger);
	 		(new Thread(worker)).start();
		}

	}
	

	public static void main(String[] args) throws Exception 
	{
		//Check if there is a port number passed when starting form the command prompt
		if (args.length <= 0) {
			System.err.println("Enter the server port as a commnad line argument");
			return;
		}
		
		//Create a tcpServer, the TCP server will keep listening as long as the running.txt file exits
		//To stop a server, simply remore the running.txt file
		//This a the recommended practice
		File file = new File("src/a2/running.txt");
        PrintWriter logger = new PrintWriter("src/a2/server.log", "UTF-8");
		new Server(Integer.parseInt(args[0]), file, logger);
		

	}

}
