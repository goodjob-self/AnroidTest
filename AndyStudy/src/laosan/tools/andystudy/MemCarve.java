package laosan.tools.andystudy;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract.Constants;

public class MemCarve {
	final private static String memCarvTagString = "MEMCARV";
	private static final String TABLENAME = "memcarve";
	/**
	 * @return the tablename
	 */
	public static String getTablename() {
		return TABLENAME;
	}
	public static String aa() {
		return null;
		
	}
	public static int MemDays [] = {1,3,7,10,20,40,80,160,320,640}; 
	/**
	 * @return the memDays
	 */
	public static int[] getMemDays() {
		return MemDays;
	}
	/**
	 * @param memDays the memDays to set
	 */
	public static void setMemDays(int[] memDays) {
		MemDays = memDays;
	}

	/**
	 * @return the memcarvtagstring
	 */
	public static String getMemcarvtagstring() {
		return memCarvTagString;
	}
	public boolean initMemdays(String memCarveString){
		boolean ret = false;
		String[] carvestrings = memCarveString.split(",");
		int[] memdays = new int[carvestrings.length];
		for (int i = 0; i < carvestrings.length; i++) {
			try {
				memdays[i] = Integer.valueOf(carvestrings[i]).intValue();
			} catch (Exception e) {
				return false;
			}
		}
		MemCarve.setMemDays(memdays);
		return true;
	}
	public void saveMemdays(){
		
	}
}
