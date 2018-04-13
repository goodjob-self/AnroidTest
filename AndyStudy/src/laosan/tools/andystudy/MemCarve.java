package laosan.tools.andystudy;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract.Constants;

public class MemCarve {
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
	private SharedPreferences ShP;
	public MemCarve(Context cntx) {
		// TODO Auto-generated constructor stub
		try {
			ShP = cntx.getSharedPreferences(MainAct.getMemCarve(), 0);
			SharedPreferences.Editor editor = ShP.edit();
			//editor.putString(MemDays.toString(), );
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
