package laosan.tools.andystudy;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainAct extends Activity{
	static private final String MemCarve = "MemCarve";

	
	Button todayRevButton;
	Button yesterdayButton;
	Button todayNewButton;
	Button otherButton;
	Button tomorrowButton;
	Button submitButton;
	ListView lessonsListView;
	EditText addlessonText;
	
	
	public MainAct() {
		// TODO Auto-generated constructor stub
	}
	void init_data(){
		lessonsMap = LessonPreferences.getAll();
		revTimesMap = RevTimesPreferences.getAll();
		//LessonPreferences.
	}
	void initControl(){
		todayNewButton = (Button) findViewById(R.id.btTodayNew);
		todayRevButton = (Button) findViewById(R.id.btTodayRv);
		yesterdayButton = (Button) findViewById(R.id.btYstdNew);
		otherButton = (Button) findViewById(R.id.btOther);
		tomorrowButton = (Button) findViewById(R.id.btTomorrowRv);
		submitButton = (Button) findViewById(R.id.btSubmit);
		addlessonText = (EditText) findViewById(R.id.editText1);
		lessonsListView  = (ListView) findViewById(R.id.listView1);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviewlayout);

		init_data();
		initControl();
		Toast.makeText(this, getResources().getString(R.string.today_review), 5000);
		todayNewButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		todayRevButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		yesterdayButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		otherButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		tomorrowButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});			
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	/**
	 * @return the memCarve
	 */
	static public String getMemCarve() {
		return MemCarve;
	}
	
}
