package laosan.tools.andystudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "reviewinfor.db";  
    private static final int DATABASE_VERSION = 1;  
      
    public DBHelper(Context context) {  
        //CursorFactory����Ϊnull,ʹ��Ĭ��ֵ  

        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
  
    //���ݿ��һ�α�����ʱonCreate�ᱻ����  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ReviewInfoItem.getTablename()+
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ReviewInfoItem.getStartTag()+" VARCHAR, "
        		+ ReviewInfoItem.getLessonTag()+" VARCHAR, "+ReviewInfoItem.getLastTag()+" VARCHAR, "
        		+ ReviewInfoItem.getReviewTag()+" INTEGER)");  
        db.execSQL("CREATE TABLE IF NOT EXISTS " + MemCarve.getTablename() + 
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+MemCarve.getMemcarvtagstring()+" VARCHAR)");  
    }  
  
    //���DATABASE_VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        db.execSQL("ALTER TABLE "+ReviewInfoItem.getTablename()+" ADD COLUMN other STRING");  
    }  
}
