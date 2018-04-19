package org.selfwork.andystudy.ui;

import java.util.List;

import org.selfwork.andystudy.activity.MainActivity;
import org.selfwork.andystudy.data.ReviewInfoItem;

import laosan.tools.andystudy.R;

import android.R.integer;
import android.R.raw;
import android.content.Context;
import android.filterfw.core.FinalPort;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	Context context;
	List<ReviewInfoItem> list;
	LayoutInflater inflater;
	ViewHolder v;
	int mposition;
	Handler mHandler;
	MyPopWin mPopWin;
	
	public MyAdapter() {
	}

	public MyAdapter(Context context, List<ReviewInfoItem> list, Handler hdlr) {
		this.list = list;
		this.context = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mHandler = hdlr;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			v = new ViewHolder();
			convertView = inflater.inflate(R.layout.listadapter, null);
			v.t1 = (TextView) convertView.findViewById(R.id.lastTimeTextV);
			v.t2 = (TextView) convertView.findViewById(R.id.lessonsTextV);
			v.t3 = (TextView) convertView.findViewById(R.id.SatusTextV);
			v.cBox = (CheckBox) convertView.findViewById(R.id.rvcheckBox);
		
			v.cBox.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					mposition = position;
					if(event.getAction()==MotionEvent.ACTION_UP){
						if(list.get(position).isFinishMark()){
							Message msgMessage = new Message();
							msgMessage.what = MainActivity.POPWINCHECKMESG;
							msgMessage.arg1 = mposition;
							msgMessage.arg2 = 0;
							mHandler.sendMessage(msgMessage);
							//list.get(mposition).setFinishMark(false);
							//notifyDataSetChanged();
						} else {
							mPopWin = new MyPopWin(context,v,R.string.confirmcheck,checkYesListener,checkNoListener,false,null);
						}
					}
					return true;
				}
			});
			convertView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					mposition = position;
					mPopWin = new MyPopWin(context,v,R.string.confirmdelete,deleteYesListener,deleteNoListener,false,null);
					return false;
				}
			});
			convertView.setTag(v);
		} else {
			v = (ViewHolder) convertView.getTag();
		}

		v.t1.setText(list.get(position).getStartDateString());
		v.t2.setText(list.get(position).getLessonString());
		v.t3.setText(list.get(position).getLastDateString());
		if(MainActivity.isbTodayReview()){
			if(list.get(position).isFinishMark()){
				v.cBox.setChecked(true);
			} else {
				v.cBox.setChecked(false);
			}
		}else {
			v.cBox.setVisibility(View.GONE);
		}
		return convertView;
	}

	public void setSelection(int position) {
		this.mposition = position;
	}

	public void setDatalist(List<ReviewInfoItem> list) {
		this.list = list;
	}

	public void addDatas(List<ReviewInfoItem> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	
	
	class ViewHolder {
		TextView t1, t2, t3;
		CheckBox cBox;
	}
	//public static boolean checkstatus = false;

	OnTouchListener checkYesListener = new OnTouchListener() {		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_UP){
				Message msgMessage = new Message();		
				msgMessage.what = MainActivity.POPWINCHECKMESG;
				msgMessage.arg1 = mposition;
				msgMessage.arg2 =1;
				mHandler.sendMessage(msgMessage);
				//list.get(mposition).setFinishMark(true);
				//notifyDataSetChanged();
				mPopWin.dismiss();
			}
			return false;
		}
	};
	OnTouchListener checkNoListener = new OnTouchListener() {	
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_UP){
				Message msgMessage = new Message();
				msgMessage.what = MainActivity.POPWINCHECKMESG;
				msgMessage.arg1 = mposition;
				msgMessage.arg2 = 0;
				mHandler.sendMessage(msgMessage);
//				list.get(mposition).setFinishMark(false);
//				notifyDataSetChanged();
				mPopWin.dismiss();
			}
			return false;
		}
	};
	OnTouchListener deleteYesListener = new OnTouchListener() {	
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			Message msgMessage = new Message();
			msgMessage.what = MainActivity.POPWINDELETEMESG;
			msgMessage.arg1 = mposition;
			mHandler.sendMessage(msgMessage);
			mPopWin.dismiss();
			return false;
		}
	};
	OnTouchListener deleteNoListener = new OnTouchListener() {		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			mPopWin.dismiss();
			return false;
		}
	};
}
