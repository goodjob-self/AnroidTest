package laosan.tools.andystudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "reviewinfor.db";  
    private static final int DATABASE_VERSION = 1;  
      
    public DBHelper(Context context) {  
        //CursorFactory设置为null,使用默认值  

        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
  
    //数据库第一次被创建时onCreate会被调用  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ReviewInfoItem.getTablename()+
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ReviewInfoItem.getStartTag()+" VARCHAR, "
        		+ ReviewInfoItem.getLessonTag()+" VARCHAR, "+ReviewInfoItem.getLastTag()+" VARCHAR, "
        		+ ReviewInfoItem.getReviewTag()+" INTEGER)");  
        db.execSQL("CREATE TABLE IF NOT EXISTS " + MemCarve.getTablename() + 
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+MemCarve.getMemcarvtagstring()+" VARCHAR)");  
    }  
  
    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        db.execSQL("ALTER TABLE "+ReviewInfoItem.getTablename()+" ADD COLUMN other STRING");  
    }  
}
