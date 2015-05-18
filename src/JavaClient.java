
import org.apache.log4j.BasicConfigurator;
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.inbound.InboundConnectionFailure;

import java.io.IOException;
import java.util.Scanner;


public class JavaClient
{

	private String host = "localhost";
	private int port = 8021;
	private String password = "ClueCon";
	private Client client;

	public void do_connect() throws InterruptedException
	{
		client = new Client();
		client.addEventListener(new EslEventListener());

		System.out.println("Client is attempting to connect to FreeSwitch server, " + host + ":" + port);
		try 
		{
			client.connect(host, port, password, 2);
		} 
		catch (InboundConnectionFailure e)
		{
			System.out.println("Oops! Connection to localhost:8021 failed..\nLooks like FreeSwitch server is not up and running! EXITING!!!");
			System.exit(0);
		}
		
		System.out.println("Client connected .. ");
		
		client.setEventSubscriptions("plain", "all");    //Subscribing for all the events.
		
		System.out.print("Events are now being logged to EventLogs.txt file. \n"
				         + "Enter \"QUIT\" to exit logging and application: ");

	}
	

	public void close_client()
	{
		client.close();
	}

	public static void main(String[] args)
	{
		JavaClient test = new JavaClient();
		BasicConfigurator.configure();
		try
		{
			MyLogger.setup();
			test.do_connect();
			Scanner input = new Scanner(System.in);
			while(true)
			{
				String str = input.next();
				if( str.equalsIgnoreCase("Quit"))
					break;
			}
			input.close();
			test.close_client();
		} 
		catch (IllegalStateException e)
		{
			System.out.println("Error while closing connection. Looks like FreeSwitch server had alredy been shut down...");
		}
		catch (InterruptedException ie)
		{
			return;
		}
		catch (IOException e) 
		{
		     e.printStackTrace();
		     throw new RuntimeException("Problems with creating the log files");
		}
		finally
		{
			System.out.println("Please check EventLogs.txt file for event logs.");
			System.out.println("Exiting!");
			MyLogger.clean();
			System.exit(0);
		}
	}
}
