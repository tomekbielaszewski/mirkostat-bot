package old.org.grizz.utils;

public class StringPlural {
	//plurals[0]: jeden komputer
	//plurals[1]: dwa-cztery komputery
	//plurals[2]: piec-dwadziescia_jeden komputerow
	//plurals[2]: zero komputerow
	
	public static String choose(String[] plurals, int amount) {
		if(amount <= 21) {
			if(amount == 0) {
				return plurals[2];
			} else if(amount == 1) {
				return plurals[0];
			} else if(amount >= 2 && amount <= 4) {
				return plurals[1];
			} else { //(amount >= 5) 
				return plurals[2];
			}
		} else {
			String amount_s = "" + amount;
			String lastDigit = amount_s.substring(amount_s.length()-1, amount_s.length());
			if(lastDigit.equals("2") ||
					lastDigit.equals("3") ||
					lastDigit.equals("4")) {
				return plurals[1];
			} else {
				return plurals[2];
			}
		}
	}
}
