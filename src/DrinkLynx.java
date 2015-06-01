// Basic Usage Server: "DrinkLynx [-s] [Port Number]
// Basic Usage Client: "DrinkLynx [hostName] [Port#] [StringData]

import java.io.IOException;

public class DrinkLynx {

	public static void main(String[] args) throws IOException {
		
		//Server
		if (args.length == 2) {
			if (args[0].equals("-s")) {
				int port = Integer.parseInt(args[1]);
				DrinkLynxServer server = new DrinkLynxServer(port);
				server.listen();
			}
		}
		//Client
		else {
			String HostName = args[0];
			int PortNumber = Integer.parseInt(args[1]);
			String StringData = args[2];
			DrinkLynxClient client = new DrinkLynxClient(HostName, PortNumber, StringData);
			client.connect();
		}
	}
}