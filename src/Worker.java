package a2;
/*
 * The worker class is created for every request that comes into the server.
 * The worker class Makes sure that clients requests is handled correctly
 * It is run in separate thread
 */
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Worker implements Runnable 
{
   private Socket client;
   private PrintStream out;
   private PrintWriter logger;
   

   
   public Worker(Socket client, PrintWriter logger) throws Exception {
	   this.client = client;
	   this.logger = logger;
	   this.out = new PrintStream(client.getOutputStream());
   }
   
   public void handle() throws Exception {
	   
	   		Date currentDate = new Date();
	   		//Put the current date into the logger file "Server.log"
            logger.println(String.format("%s %s %s", client.getInetAddress(), client.getPort(), currentDate.toString()));
            //Send the current time along with the requesting client to the client
	        out.println(String.format("%s %s %s", client.getInetAddress(), client.getPort(), currentDate.toString()));
	        //Wait for 10 seconds to ensure that there is an overlap between the client and the server
	   		Thread.sleep(10000);
   }
   
   
   /*
    * This is the first method that gets executed whenever a thread is started
    * Within this method, the handle method is executed of the worker class
    */
	@Override
	public void run() {
		try {
			this.handle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	   
}
