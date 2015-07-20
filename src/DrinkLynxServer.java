import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DrinkLynxServer {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private int portNumber;
	private DataInputStream is;
	private DataOutputStream os;	
	private Date Time = new Date();
	private boolean isRunning = true;
	private String UserCords;
	private String clientName;
	ArrayList<String> DrinkValues = new ArrayList<String>();
	
	SimpleDateFormat sdf = new SimpleDateFormat ("[hh:mm:ss a] ");

	public DrinkLynxServer(int port) {
		this.portNumber = port;
	}

	public void listen() throws IOException {

		System.out.println("Server is listening on port: " + portNumber);

		while (isRunning) {
			try {
				// Create Server
				System.out.println();
				serverSocket = new ServerSocket(portNumber);
				System.out.println(sdf.format(Time) + " Server is listening on port: " + portNumber);
				
				clientSocket = serverSocket.accept();
				clientName = clientSocket.getInetAddress().getHostName();
				System.out.println(sdf.format(Time) + " New Client Connected: " + clientName);
				
				is = new DataInputStream(clientSocket.getInputStream());
				os = new DataOutputStream(clientSocket.getOutputStream());
				
				// Get User Coordinates
				System.out.println(sdf.format(Time) + " Listening for Bar Data");
				UserCords = is.readUTF();
				System.out.println(sdf.format(Time) + " Recieved: " + UserCords);
				
				//Load Bar Drink Data
				HashDB DrinkData = new HashDB(UserCords);
				DrinkValues = DrinkData.getBarValue();
				System.out.println(sdf.format(Time) + " Drink Data Loaded: " + DrinkValues);
				
				// Send String To Client
				int ValuesSize = DrinkValues.size();
				
				for (int i = 0; i < ValuesSize; i++) {
					os.writeUTF(DrinkValues.get(i));
					System.out.println(sdf.format(Time) + " Sent to client: " + DrinkValues.get(i));
				}

				// Close All The Things
				clientSocket.close();
				System.out.println(sdf.format(Time) + " Closed Client Socket");
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			} finally {
				serverSocket.close();
				System.out.println(sdf.format(Time) + " Closing server socket...");
				System.out.println(); 
				System.out.println("************************************************************");
			}
		}
	}
}
