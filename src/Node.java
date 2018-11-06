package a2;
/*
 * This class will keep listening until a command to attack a particular server comes in.
 * When a command comes to attack a particular machine, the Server will send a request to the target 
 * To start the server, simply pass in the attackedServerIP:PORT and the port number that will be associated with
 * the server in the command prompt
 * To stop the server, simply force the server to stop using CTRL + C
 */
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Node {
	
	public Node(String[] args) throws UnknownHostException, IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        //Keep the server running forever until it is forced to exit
		while (true)
		{
			//Open a socket connection and let the worker class handle it in a separate thread
 			Socket client = server.accept();
 			//
 			@SuppressWarnings("resource")
			Scanner in = new Scanner(client.getInputStream()); 
 		    PrintStream out = new PrintStream(client.getOutputStream());

 			String command = in.nextLine();
 			
 			if (command.toLowerCase().contains("attack")) {
 				//Send the attack to the server
 				String[] ipPort = args[1].split(":");
 			    @SuppressWarnings("resource")
				Socket socket = new Socket(ipPort[0], Integer.parseInt(ipPort[1]));
		        //get both the input and output stream of the socket
		    	PrintStream outServer = new PrintStream(socket.getOutputStream());
		    	@SuppressWarnings("resource")
				Scanner inServer = new Scanner(socket.getInputStream()); 
		    	outServer.println("I am attacking you");
		    	
		    	String response = inServer.nextLine();
		    	System.out.println(response);
		    	out.println(args[0] + " " + response);
 			}
 			
		}
	}
	public static void main(String[] args)  {
		//If there is no arguments in the command line, throw an error
		if (args.length <= 1) {
			System.err.println("Add the commandline arguments in the following formate 'AttackedServerIP:PORT SERVER_PORT'");
			return;
		}
		
		//Start the server to listen for attacks
		try {
			new Node(args);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
}
