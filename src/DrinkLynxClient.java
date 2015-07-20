import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.UnknownHostException;

public class DrinkLynxClient {
	
	Socket clientSocket = null;
    DataOutputStream os = null;
    DataInputStream is = null;
	
	private String HostName;
	private int PortNumber;
	private String StringX;
	private String StringY;
	private String UserLocation = "";
	private String BarData;
	
	public DrinkLynxClient(String HostName, int PortNumber, String X, String Y) {
		this.HostName = HostName;
		this.PortNumber = PortNumber;
		this.StringX = X;
		this.StringY = Y;
		UserLocation = UserLocation.concat(X);
		UserLocation = UserLocation.concat(" " + Y);
	}
	
	// Connect To Server : Request Bar Drink Specials
	public void connect() {
		
		try {
			//Create Client
			clientSocket = new Socket(HostName, PortNumber);
			os = new DataOutputStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
			System.out.println("Client is connected to the server at host: " + HostName + " on port: " + PortNumber);
			
			// Ask For Bar Drink Specials
			os.writeUTF(UserLocation);
			System.out.println("Sending Data: " + UserLocation);
			
			// Read Bar Specials
			BarData = is.readUTF();
			System.out.println(BarData);
		} 
		catch (UnknownHostException e) {
			System.err.println("<!> Unknown Host: " + HostName);
			e.printStackTrace();
			System.exit(-1);
		} 
		catch (IOException e) {
	    System.err.println("<!> Couldn't get I/O for the connection to " + HostName);
	    e.printStackTrace();
	    System.exit(-1);
		}
		finally {
			// Close Socket
			try {
				System.out.println("Done...exiting...");
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
