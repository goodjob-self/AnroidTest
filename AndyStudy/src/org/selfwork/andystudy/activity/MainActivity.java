package org.selfwork.andystudy.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.selfwork.andystudy.data.DBManager;
import org.selfwork.andystudy.data.MemCarve;
import org.selfwork.andystudy.data.ReviewInfoItem;
import org.selfwork.andystudy.ui.MyAdapter;
import org.selfwork.andystudy.ui.MyPopWin;

import laosan.tools.andystudy.R;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageParser.NewPermissionInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends Activity{
	public static final int POPWINCHECKMESG = 0x1000;
	public static final int POPWINDELETEMESG = 0x2000;
	private static boolean bTodayReview = true;
	DBManager dbmngr;
	
	Button todayRevButton;
	Button yesterdayButton;
	Button todayNewButton;
	Button otherButton;
	Button tomorrowButton;
	Button submitButton;
	Button yesButton;
	Button noButton;
	ListView lessonsListView;
	EditText addlessonText;
	List <ReviewInfoItem> mALLList;
	List <ReviewInfoItem> mactivList;
	MyAdapter mcurAdapter ;
	MyPopWin addPopupWindow ;
	Handler mHandler;
	
	public MainActivity() {
		// TODO Auto-generated constructor stub
	}
	void init_data(){
//		lessonsMap = LessonPreferences.getAll();
//		revTimesMap = RevTimesPreferences.getAll();
		//LessonPreferences.
		dbmngr = new DBManager(this);
		mALLList = dbmngr.query();
		
		String memCarveString =	dbmngr.getMemCarve();
		new MemCarve().initMemdays(memCarveString);
		
		mactivList = new ArrayList<ReviewInfoItem>();
		
		mHandler = new Handler(){
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case POPWINCHECKMESG:
					ReviewInfoItem reviewinfo = mactivList.get(msg.arg1);
					reviewinfo.setFinishMark((msg.arg2==1)?true:false);
					if(msg.arg2==1){
						reviewinfo.setLastDateString(sdf1.format(new Date()));
						if((reviewinfo.getReviewTimes()+1)<MemCarve.getMemDays().length){
							reviewinfo.setReviewTimes(reviewinfo.getReviewTimes()+1);
							dbmngr.updateReviewTime(reviewinfo);
							mALLList = dbmngr.query();
						}
					}
					mcurAdapter.notifyDataSetChanged();
					break;
				case POPWINDELETEMESG:
					mactivList.get(msg.arg1).get_id();
					dbmngr.deleteOldReviewInfo(mactivList.get(msg.arg1));
					mALLList = dbmngr.query();
					mactivList.remove(msg.arg1);
					mcurAdapter.notifyDataSetChanged();
					break;
				default:
					break;
				}
			}
			
		};
		mcurAdapter = new MyAdapter(this, mactivList,mHandler);
	}
	void initControl(){
		todayNewButton = (Button) findViewById(R.id.btTodayNew);
		todayRevButton = (Button) findViewById(R.id.btTodayRv);
		yesterdayButton = (Button) findViewById(R.id.btYstdNew);
		otherButton = (Button) findViewById(R.id.btOther);
		tomorrowButton = (Button) findViewById(R.id.btTomorrowRv);
		submitButton = (Button) findViewById(R.id.btSubmit);
		lessonsListView  = (ListView) findViewById(R.id.listView1);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviewlayout);

		init_data();
		initControl();
		ToastInfor(R.string.today_review);

		todayNewButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				setbTodayReview(false);
				lessonsListView.setVisibility(View.VISIBLE);
				lessonsListView.setFocusable(true);
				lessonsListView.setFocusableInTouchMode(true);
				lessonsListView.requestFocus();
				if(pickTodayNew()!=0){
					lessonsListView.setAdapter(mcurAdapter);
					mcurAdapter.addDatas(mactivList);
				}else{
					lessonsListView.setVisibility(View.GONE);
					ToastInfor(R.string.noinfor);
				}
				return false;
			}
		});
		todayRevButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				setbTodayReview(true);
				lessonsListView.setVisibility(View.VISIBLE);
				if(pickTodayReview()!=0){
					lessonsListView.setAdapter(mcurAdapter);
					mcurAdapter.addDatas(mactivList);
				} else {
					lessonsListView.setVisibility(View.GONE);
					ToastInfor(R.string.noinfor);
				}
				return false;
			}
		});
		yesterdayButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				setbTodayReview(false);
				lessonsListView.setVisibility(View.VISIBLE);
				if(pickYestodayReview()!=0){
					lessonsListView.setAdapter(mcurAdapter);
					mcurAdapter.addDatas(mactivList);
				} else {
					lessonsListView.setVisibility(View.GONE);
					ToastInfor(R.string.noinfor);
				}
				return false;
			}
		});
		otherButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_UP){
					lessonsListView.setVisibility(View.GONE);
					addPopupWindow = new MyPopWin(MainActivity.this,v,R.string.memcarve,yesMemCarveListener, noMemCarveListener,true,dbmngr.getMemCarve());
				}
				return false;
			}
		});
		tomorrowButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				setbTodayReview(false);
				lessonsListView.setVisibility(View.VISIBLE);
				if(pickTomorrowReview() != 0){
					lessonsListView.setAdapter(mcurAdapter);
					mcurAdapter.addDatas(mactivList);
				} else {
					lessonsListView.setVisibility(View.GONE);
					ToastInfor(R.string.noinfor);
				}
				return false;
			}
		});	
		submitButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_UP){
					lessonsListView.setVisibility(View.GONE);
					addPopupWindow = new MyPopWin(MainActivity.this,v,R.string.addlesson,yesAddButtonListener, noAddButtonListener,true,null);
				}
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

	int pickTodayReview(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

		mactivList.clear();
		mcurAdapter.notifyDataSetChanged();
		for(ReviewInfoItem reviewinfo : mALLList){/*today-offday>=lastday*/
			Calendar calendar1 = Calendar.getInstance();
			if(reviewinfo.getReviewTimes()>(MemCarve.getMemDays().length-1)){
				repairMemCarve();
			}
			calendar1.add(Calendar.DATE,-MemCarve.getMemDays()[ reviewinfo.getReviewTimes()]);
			String timesString = sdf1.format(calendar1.getTime());
			Log.e("time", timesString);
			if(Integer.valueOf(timesString).longValue()>=Integer.valueOf(reviewinfo.getLastDateString()).longValue()){
				mactivList.add(reviewinfo);
			}
		}
		return mactivList.size();
	}
	int pickTodayNew(){
		Calendar calendar1 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		String today = sdf1.format(calendar1.getTime());

		mactivList.clear();
		mcurAdapter.notifyDataSetChanged();
		for(ReviewInfoItem reviewinfo : mALLList){/*today==lastday*/
			if(Integer.valueOf(today).longValue()==Integer.valueOf(reviewinfo.getStartDateString()).longValue()){
				mactivList.add(reviewinfo);
			}
		}
		return mactivList.size();
	}
	int pickYestodayReview(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

		mactivList.clear();
		mcurAdapter.notifyDataSetChanged();
		for(ReviewInfoItem reviewinfo : mALLList){/*today-1==lastday*/
			Calendar calendar1 = Calendar.getInstance();
			calendar1.add(Calendar.DATE,-1);
			String timesString = sdf1.format(calendar1.getTime());
			Log.e("time", timesString);
			if(Integer.valueOf(sdf1.format(calendar1.getTime())).longValue()==Integer.valueOf(reviewinfo.getStartDateString()).longValue()){
				mactivList.add(reviewinfo);
			}
		}
		return mactivList.size();
	}
	@SuppressWarnings("deprecation")
	int pickTomorrowReview(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

		//new date()
		mactivList.clear();
		mcurAdapter.notifyDataSetChanged();
		for(ReviewInfoItem reviewinfo : mALLList){/*today+1-offday>=lastday*/
			Calendar calendar1 = Calendar.getInstance();
			if(reviewinfo.getReviewTimes()>(MemCarve.getMemDays().length-1)){
				repairMemCarve();
			}
			calendar1.add(Calendar.DATE,1-MemCarve.getMemDays()[ reviewinfo.getReviewTimes()]);
			String timesString = sdf1.format(calendar1.getTime());
			Log.e("time", timesString);
			if(Integer.valueOf(timesString).longValue()>=Integer.valueOf(reviewinfo.getLastDateString()).longValue()){
				mactivList.add(reviewinfo);
			}
		}
		return mactivList.size();
	}
	void addNewLesson(String lesson){
		ReviewInfoItem reviewInfoItem = new ReviewInfoItem();
		Calendar calendar1 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		String days = sdf1.format(calendar1.getTime());
		
		reviewInfoItem.setStartDateString(days);
		reviewInfoItem.setLessonString(lesson);
		reviewInfoItem.setLastDateString(days);
		reviewInfoItem.setReviewTimes(0);
		List<ReviewInfoItem> reviewlistInfoItems =new ArrayList<ReviewInfoItem>();
		reviewlistInfoItems.add(reviewInfoItem);
		dbmngr.add(reviewlistInfoItems);
		mALLList = dbmngr.query();
	}

//	void popAddLessonWin(View v){
//		View contentView=LayoutInflater.from(MainAct.this).inflate(R.layout.poptextedit, null);
//		if(addPopupWindow==null){
//			addPopupWindow = new PopupWindow(contentView,1080,1080,true);
//			yesButton = (Button) contentView.findViewById(R.id.btYes);
//			noButton = (Button) contentView.findViewById(R.id.btNo);
//			addlessonText = (EditText) contentView.findViewById(R.id.editText1);
//			yesButton.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					String lessonString = addlessonText.getText().toString();
//					if(!lessonString.equals("")&&lessonString!=null){
//						addNewLesson(lessonString);
//						ToastInfor(R.string.addsucess);
//						addPopupWindow.dismiss();
//						addPopupWindow = null;
//					}
//					return false;
//				}
//			});
//			noButton.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					addPopupWindow.dismiss();
//					addPopupWindow = null;
//					return false;
//				}
//			});
//		}
//		addPopupWindow.showAsDropDown(v);
//	}
	OnTouchListener yesAddButtonListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			String lessonString = addPopupWindow.getEditTextInfo();
			if(!lessonString.equals("")&&lessonString!=null){
				addNewLesson(lessonString);
				ToastInfor(R.string.addsucess);
				addPopupWindow.dismiss();
				addPopupWindow = null;
			}
			return false;
		}
	};
	OnTouchListener noAddButtonListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			addPopupWindow.dismiss();
			addPopupWindow = null;
			return false;
		}
	};
	OnTouchListener yesMemCarveListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			String crvString = addPopupWindow.getEditTextInfo();
			if(!crvString.equals("")&&crvString!=null){
				if(new MemCarve().initMemdays(crvString)){
					dbmngr.setMemCarve(crvString);
					ToastInfor(R.string.modifysucess);
					addPopupWindow.dismiss();
					addPopupWindow = null;
				}else{
					ToastInfor(R.string.modifyfail);
				}
			}
			return false;
		}
	};
	OnTouchListener noMemCarveListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			addPopupWindow.dismiss();
			addPopupWindow = null;
			return false;
		}
	};
	void repairMemCarve(){
		String carvString = "";
		int maxReviewTimes = 0;
		for(ReviewInfoItem tmpItem :mALLList){
			maxReviewTimes = (tmpItem.getReviewTimes()>maxReviewTimes)?tmpItem.getReviewTimes():maxReviewTimes;
		}
		int[] orgMemDays = MemCarve.getMemDays();
		if((maxReviewTimes+1)>=orgMemDays.length){
			maxReviewTimes +=1;
			int[] newMemDays = new int[maxReviewTimes];
			for (int i = orgMemDays.length; i < newMemDays.length; i++) {
				newMemDays[i] = orgMemDays[orgMemDays.length-1];
			}
			MemCarve.setMemDays(newMemDays);
    		for (int i = 0; i < newMemDays.length; i++) {
				carvString+=String.valueOf(newMemDays[i]);
				if(i!=(newMemDays.length-1)){
					carvString+=",";
				}
			}
			dbmngr.setMemCarve(carvString);
		}
	}
	void ToastInfor(int StringId){
		Toast.makeText(this, getResources().getString(StringId), Toast.LENGTH_SHORT).show();
	}
	/**
	 * @return the bTodayReview
	 */
	static public boolean isbTodayReview() {
		return bTodayReview;
	}
	/**
	 * @param bTodayReview the bTodayReview to set
	 */
	static public void setbTodayReview(boolean bTodayReview) {
		MainActivity.bTodayReview = bTodayReview;
	}
}
