
import java.util.HashMap;

public class HashDB {
	
	 String BarKey;
	 String BarValue;
	
	//Default Constructor
	public HashDB(String Key) {
		
		BarKey = Key;
		
		HashMap<String, String> HashDB = new HashMap<>();
		
		HashDB.put("Pazzos", "Crown Royal,5.00,Jameson,3.50,Bacardi,5.00");
		HashDB.put("Exchequer", "Bloody Mary,5.00,Mimosas,4.00,Corona Bucket,15.00");
		
		BarValue = HashDB.get(BarKey);
	}
	
	public String getBarValue() {
		return BarValue;
	}
}
