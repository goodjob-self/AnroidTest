package laosan.tools.andystudy;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

public class MyPopWin {
	PopupWindow addPopupWindow ;
	EditText editTextinfo;
	public MyPopWin() {
		// TODO Auto-generated constructor stub
	}
	public MyPopWin(Context context,View v,int titleStringId,OnTouchListener yesListener,OnTouchListener noListener,boolean bEditable){
		View contentView=LayoutInflater.from(context).inflate(R.layout.poptextedit, null);
		addPopupWindow = new PopupWindow(contentView,1080,1080,true);
		Button yesButton = (Button) contentView.findViewById(R.id.btYes);
		Button noButton = (Button) contentView.findViewById(R.id.btNo);
		TextView titleTextView = (TextView) contentView.findViewById(R.id.popinfor);
		titleTextView.setPadding(0, 0, 160, 0);
		titleTextView.setText(titleStringId);
		editTextinfo = (EditText) contentView.findViewById(R.id.editText1);
		if(bEditable){
			editTextinfo.setVisibility(View.VISIBLE);
		} else {
			editTextinfo.setVisibility(View.GONE);
		}
		addPopupWindow.setOutsideTouchable(false);

		yesButton.setOnTouchListener(yesListener);
		noButton.setOnTouchListener(noListener);
		//addPopupWindow.setd
		addPopupWindow.showAsDropDown(v);
	}
	void dismiss(){
		try {
			addPopupWindow.dismiss();			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getEditTextInfo(){
		return editTextinfo.getText().toString();
	}
}
