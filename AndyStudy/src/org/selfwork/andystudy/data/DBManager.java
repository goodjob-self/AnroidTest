package org.selfwork.andystudy.data;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;


import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SqliteWrapper;
import android.util.Log;
import android.view.View;

public class DBManager {
//	private final String DBNAME = "reviewinfor.db";
	
    private DBHelper helper;  
    private SQLiteDatabase db;  
      
    public DBManager(Context context) {  
        try {
			helper = new DBHelper(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里  
        db = helper.getWritableDatabase();  
       // db.execSQL("CREATE TABLE IF NOT EXISTS " + MemCarve.getTablename() + 
       //         " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+MemCarve.getMemcarvtagstring()+" VARCHAR)");
    }  
      
    /** 
     * add persons 
     * @param persons 
     */  
    public void add(List<ReviewInfoItem> reviewinfos) {  
        db.beginTransaction();  //开始事务  
        try {  
            for (ReviewInfoItem reviewinfo : reviewinfos) {  
                db.execSQL("INSERT INTO reviewinfo VALUES(null, ?, ?, ?, ?)", 
                		new Object[]{reviewinfo.getStartDateString(), reviewinfo.getLessonString(), reviewinfo.getLastDateString(),reviewinfo.getReviewTimes()});  
            }  
            db.setTransactionSuccessful();  //设置事务成功完成  
        } finally {  
            db.endTransaction();    //结束事务  
        }  
    }  
      
    /** 
     * update reviewinfo's age 
     * @param reviewinfo 
     */  
    public void updateReviewTime(ReviewInfoItem reviewinfo) {  
        ContentValues cv = new ContentValues();  
        cv.put(ReviewInfoItem.getLastTag(), reviewinfo.getLastDateString());  
        cv.put(ReviewInfoItem.getReviewTag(), reviewinfo.getReviewTimes());  
        db.update("reviewinfo", cv, "_id = ?", new String[]{String.valueOf(reviewinfo.get_id())});  
    }  
      
    /** 
     * delete old reviewinfo 
     * @param reviewinfo 
     */  
    public void deleteOldReviewInfo(ReviewInfoItem reviewinfo) {  
        db.delete("reviewinfo", "_id = ?", new String[]{String.valueOf(reviewinfo.get_id())});  
    }  
      
    /** 
     * query all reviewinfo, return list 
     * @return List<ReviewInfoItem> 
     */  
    public List<ReviewInfoItem> query() {  
        ArrayList<ReviewInfoItem> reviewinfos = new ArrayList<ReviewInfoItem>();  
        Cursor c = queryTheCursor();  
        while (c.moveToNext()) {  
            ReviewInfoItem reviewinfo = new ReviewInfoItem();  
            reviewinfo.set_id(c.getInt(c.getColumnIndex("_id")));  
            reviewinfo.setLessonString(c.getString(c.getColumnIndex(ReviewInfoItem.getLessonTag())));  
            reviewinfo.setReviewTimes(c.getInt(c.getColumnIndex(ReviewInfoItem.getReviewTag())));  
            reviewinfo.setStartDateString(c.getString(c.getColumnIndex(ReviewInfoItem.getStartTag())));  
            reviewinfo.setLastDateString(c.getString(c.getColumnIndex(ReviewInfoItem.getLastTag())));  

            reviewinfos.add(reviewinfo);  
        }  
        c.close();  
        return reviewinfos;  
    }  
      
    /** 
     * query all reviewinfo, return cursor 
     * @return  Cursor 
     */  
    public Cursor queryTheCursor() {  
        Cursor c = db.rawQuery("SELECT * FROM "+ReviewInfoItem.getTablename(), null);  
        return c;  
    }  
      
    public void setMemCarve(String crvString){
    	String carvString = null;
    	int id = 0;
    	Cursor c = db.rawQuery("SELECT * FROM "+MemCarve.getTablename(), null); 
    	while (c.moveToNext()) {  
    		id = c.getInt(c.getColumnIndex("_id"));
    		carvString = c.getString(c.getColumnIndex(MemCarve.getMemcarvtagstring()));
        }
    	if(carvString==null){
            db.beginTransaction();  //开始事务  
            try {  
                db.execSQL("INSERT INTO "+MemCarve.getTablename()+" VALUES(null, ?)", new Object[]{crvString});   
                db.setTransactionSuccessful();  //设置事务成功完成  
            } finally {  
                db.endTransaction();    //结束事务  
            }
    	} else {
    		ContentValues cv = new ContentValues();  
            cv.put(MemCarve.getMemcarvtagstring(), crvString);    
            db.update(MemCarve.getTablename(), cv, "_id = ?", new String[]{String.valueOf(id)});  
		}
    }
    public String getMemCarve(){
    	String carvString = null;
    	int id = 0;
    	Cursor c = db.rawQuery("SELECT * FROM "+MemCarve.getTablename(), null); 
    	while (c.moveToNext()) {  
    		id = c.getInt(c.getColumnIndex("_id"));
    		carvString = c.getString(c.getColumnIndex(MemCarve.getMemcarvtagstring()));
        }   	
    	if(carvString==null||carvString.equals("")){
    		carvString = "";
    		int[] dayarray = MemCarve.getMemDays();
    		for (int i = 0; i < dayarray.length; i++) {
				carvString+=String.valueOf(dayarray[i]);
				if(i!=(dayarray.length-1)){
					carvString+=",";
				}
			}
    		setMemCarve(carvString);
    	}
		return carvString;
    }
    
    /** 
     * close database 
     */  
    public void closeDB() {  
        db.close();  
    }  
    
    
    
    
//	static private final String Lessons = "Lessons";
//	static private final String RevTimes = "RevTimes";
//	static private final String MemCarve = "MemCarve";
//
//	static private final String RevTimesKey = "RevTimesKey";
//	
//	private SharedPreferences LessonPreferences ;	
//	private SharedPreferences RevTimesPreferences ;	
//	private SharedPreferences MemCarvePreferences ;
//	SharedPreferences.Editor LessonEditor ;
//	SharedPreferences.Editor RevTimesEditor ;
//	SharedPreferences.Editor MemCarvEditor ;
//	Map<String, ?> lessonsMap ;
//	Set<String> revTimesSet;
//	String [] revTimesStrings;
//	
//	int currenId;
//	Context currentCtx;
//	public DataManager(Context ctx,int viewId) {
//		LessonPreferences = ctx.getSharedPreferences(this.Lessons, 0);
//		RevTimesPreferences = ctx.getSharedPreferences(this.RevTimes, 0);
//		MemCarvePreferences = ctx.getSharedPreferences(this.MemCarve, 0);
//		try {
//			LessonEditor = LessonPreferences.edit();
//			RevTimesEditor = RevTimesPreferences.edit();
//			MemCarvEditor = MemCarvePreferences.edit();
//			lessonsMap = LessonPreferences.getAll();
//			revTimesSet = RevTimesPreferences.getStringSet(this.RevTimesKey, null);
//			revTimesStrings = (String[]) revTimesSet.toArray();			
//		} catch (Exception e) {
//			Log.e(this.toString(), "Construct err");
//		}
//
//		currenId = viewId;
//		currentCtx = ctx;
//	}
//
//	public boolean addNewLesson(String lesson){
//		Calendar calendar1 = Calendar.getInstance();
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
//		//	  calendar1.add(Calendar.DATE, -3);
//		String days = sdf1.format(calendar1.getTime());
//		revTimesSet.add(days);
//		RevTimesEditor.putStringSet(this.RevTimesKey, revTimesSet);
//		LessonEditor.putString(days, lesson);
//		refreshDate();
//		return false;
//	}
//	public boolean markMemCarve(int offset){
//		Calendar calendar1 = Calendar.getInstance();
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
//		String days = sdf1.format(calendar1.getTime());
//		int i =	MemCarvePreferences.getInt(days, 0)+1;
//		if (i<MemCarve.) {
//			
//		}
//		return false;
//	}
//	public List<ReviewInfoItem> getLessonList(int viewid){
//		lessonsMap.
//		switch (viewid) {
//		case value:
//			
//			break;
//
//		default:
//			break;
//		}
//		return null;
//		
//	}
//	void refreshDate(){
//		try {
//			lessonsMap = LessonPreferences.getAll();
//
//			revTimesSet = RevTimesPreferences.getStringSet(this.RevTimesKey, null);
//
//			revTimesStrings = (String[]) revTimesSet.toArray();				
//		} catch (Exception e) {
//			// TODO: handle exception
//		}		
//	}
}
