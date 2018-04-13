package laosan.tools.andystudy;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

public class DataManager {
	static private final String Lessons = "Lessons";
	static private final String RevTimes = "RevTimes";
	static private final String MemCarve = "MemCarve";

	static private final String RevTimesKey = "RevTimesKey";
	
	private SharedPreferences LessonPreferences ;	
	private SharedPreferences RevTimesPreferences ;	
	private SharedPreferences MemCarvePreferences ;
	SharedPreferences.Editor LessonEditor ;
	SharedPreferences.Editor RevTimesEditor ;
	SharedPreferences.Editor MemCarvEditor ;
	Map<String, ?> lessonsMap ;
	Set<String> revTimesSet;
	String [] revTimesStrings;
	
	int currenId;
	Context currentCtx;
	public DataManager(Context ctx,int viewId) {
		LessonPreferences = ctx.getSharedPreferences(this.Lessons, 0);
		RevTimesPreferences = ctx.getSharedPreferences(this.RevTimes, 0);
		MemCarvePreferences = ctx.getSharedPreferences(this.MemCarve, 0);
		try {
			LessonEditor = LessonPreferences.edit();
			RevTimesEditor = RevTimesPreferences.edit();
			MemCarvEditor = MemCarvePreferences.edit();
			lessonsMap = LessonPreferences.getAll();
			revTimesSet = RevTimesPreferences.getStringSet(this.RevTimesKey, null);
			revTimesStrings = (String[]) revTimesSet.toArray();			
		} catch (Exception e) {
			Log.e(this.toString(), "Construct err");
		}

		currenId = viewId;
		currentCtx = ctx;
	}

	public boolean addNewLesson(String lesson){
		Calendar calendar1 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		//	  calendar1.add(Calendar.DATE, -3);
		String days = sdf1.format(calendar1.getTime());
		revTimesSet.add(days);
		RevTimesEditor.putStringSet(this.RevTimesKey, revTimesSet);
		LessonEditor.putString(days, lesson);
		refreshDate();
		return false;
	}
	public boolean markMemCarve(int offset){
		Calendar calendar1 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		String days = sdf1.format(calendar1.getTime());
		int i =	MemCarvePreferences.getInt(days, 0)+1;
		return false;
	}
	public List<ReviewInfoItem> getLessonList(int viewid){
		lessonsMap.
		switch (viewid) {
		case value:
			
			break;

		default:
			break;
		}
		return null;
		
	}
	void refreshDate(){
		try {
			lessonsMap = LessonPreferences.getAll();

			revTimesSet = RevTimesPreferences.getStringSet(this.RevTimesKey, null);

			revTimesStrings = (String[]) revTimesSet.toArray();				
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
}
