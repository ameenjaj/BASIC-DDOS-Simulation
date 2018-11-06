package a2;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Coordinator {
	public static void main(String[] args) {	
		if (args.length <= 0)
			System.err.println("Please specify every nodes you would want to use to attack the server "
					+ "in the following format IP1:PORT IP2:PORT IP3:PORT");
		
		//Connect to all the nodes given in the command prompt and instruct them of an attack
		for (String arg: args) {
			String[] ipPort = arg.split(":");
			
		    try {
		    	//Create a socket and establish a connection with the node
		        Socket socket = new Socket(ipPort[0], Integer.parseInt(ipPort[1]));
		        //get both the input and output stream of the socket
		    	PrintStream out = new PrintStream(socket.getOutputStream());
		    	@SuppressWarnings("resource")
				Scanner in = new Scanner(socket.getInputStream()); 
		    	//Instruct the node to start the attack
		        out.println("Attack");
		        //Print in NODE's response
		        System.out.println(in.nextLine());
		        
		        //Close the socket
		        socket.close();  
		    } catch (Exception e) {
		        System.err.println("Don't know about host " );
		    }    
		}
	}
}
