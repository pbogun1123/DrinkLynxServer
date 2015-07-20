import java.util.ArrayList;
import java.util.HashMap;

public class HashDB {
	
	private ArrayList<String> AllBarValues = new ArrayList<String>();
	private String UserCords;
	private String[] UserXY;
	private Double userX;
	private Double userY;
	private String[] keyXY;
	private Double keyX;
	private Double keyY;
	private Double distance = 5.0;
	private String BarCors;
	private String BarDrinks;
	private String BarFinal;
	
	public HashDB(String Key) {
		
		UserCords = Key;
		UserXY = UserCords.split(" ");
		userX = Double.parseDouble(UserXY[0]);
		userY = Double.parseDouble(UserXY[1]);
		
		HashMap<String, String> HashDB = new HashMap<String, String>();
		
		HashDB.put("41.8780215,-87.6267409", "Pazzos;Crown Royal,5.00,Jameson,3.50,Bacardi,5.00");
		HashDB.put("41.877483,-87.628453", "Plymouth Bar;Corona Bucket,15.00,PBR,1.75, Captain Morgan, 4.75");
		HashDB.put("41.631221,41.631221", "Coaches Corner;Belvedere,5.00, Grey Goose,4.75, Heineken,4.25");
		HashDB.put("41.878744, -87.626595", "Exchequer;Crown Royal,4.75,Bacardi,6.75,Grey Goose,5.75");
		
		for (String Bar : HashDB.keySet()) {
			keyXY = Bar.split(",");
			keyX = Double.parseDouble(keyXY[0]);
			keyY = Double.parseDouble(keyXY[1]);
			
			if (Math.sqrt((userX-keyX)*(userX-keyX) + (userY-keyY)*(userY-keyY)) < distance) {
				
				BarCors = Bar.toString();
				BarDrinks = HashDB.get(Bar);
				BarFinal = BarCors + ";" + BarDrinks + ":";
				AllBarValues.add(BarFinal);
			}
		}
	}
	
	public ArrayList<String> getBarValue() {
		return AllBarValues;
	}
}