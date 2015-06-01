import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class DrinkLynxServer {
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private int portNumber;
	private String BarData;
	DataInputStream is;
    DataOutputStream os;
	
	public DrinkLynxServer(int port) {
		this.portNumber = port;
	}
	
	public void listen() throws IOException {
		
		System.out.println("Server is listening on port: " + portNumber);
		
		try {
			// Create Server
			serverSocket = new ServerSocket(portNumber);
			clientSocket = serverSocket.accept();
			System.out.println("New Client Connected!");
			
			is = new DataInputStream(clientSocket.getInputStream());
			os = new DataOutputStream(clientSocket.getOutputStream());
			
	        // Get Bar Name
	        BarData = is.readUTF();
	        System.out.println("Recieved: " + BarData);
	        
	        // Load Bar Drink Data
			HashDB DrinkData = new HashDB(BarData);
			String DrinkValues = DrinkData.getBarValue();
	        
			// Send String To Client
			os.writeUTF(DrinkValues);
			System.out.println("Sending: " + DrinkValues);
	        
			// Close All The Things
			clientSocket.close();
			System.out.println("Closed Client Socket");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		finally {
			serverSocket.close();
			System.out.println("Closing server socket...");
		}
	}
}

