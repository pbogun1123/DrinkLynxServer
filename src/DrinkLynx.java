// Usage Server: java DrinkLynx [-s] [Port Number]
// Usage Client: java DrinkLynx [Port#] [String X] [String Y]

import java.io.IOException;
import java.net.InetAddress;

public class DrinkLynx {

	public static void main(String[] args) throws IOException {
		
		//Server
		if (args.length == 2) {
			if (args[0].equals("-s")) {
				try {
					int port = Integer.parseInt(args[1]);
					DrinkLynxServer server = new DrinkLynxServer(port);
					server.listen();	
				} catch (NumberFormatException e) {
					System.err.println("<!> Invalid Server Usage");
					System.err.println("Server Usage: java DrinkLynx [-s] [PortNumber]");
				}	
			}
		}
		
		//Client
		else {
			try {
				int PortNumber = Integer.parseInt(args[0]);
				String StringX = args[1];
				String StringY = args[2];
				String HostName = InetAddress.getLocalHost().getHostName();
				DrinkLynxClient client = new DrinkLynxClient(HostName, PortNumber, StringX, StringY);
				client.connect();
			} catch (NumberFormatException e) {
				System.err.println("<!> Invalid Client Usage");
				System.err.println("Client Usage: java DrinkLynx [HostName] [PortNumber] [StringData]");
			}
		}
	}
}