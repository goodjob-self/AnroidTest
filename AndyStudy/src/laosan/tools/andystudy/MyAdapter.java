package laosan.tools.andystudy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	Context context;
	List<ReviewInfoItem> list;
	LayoutInflater inflater;
	ViewHolder v;
	int position;

	public MyAdapter() {
	}

	public MyAdapter(Context context, List<ReviewInfoItem> list) {
		this.list = list;
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			v = new ViewHolder();
			convertView = inflater.inflate(R.layout.adapter, null);
			v.t1 = (TextView) convertView.findViewById(R.id.lastTimeTextV);
			v.t2 = (TextView) convertView.findViewById(R.id.lessonsTextV);
			v.t3 = (TextView) convertView.findViewById(R.id.SatusTextV);
			//v.layout = (LinearLayout) convertView.findViewById(R.id.line_bg);
			convertView.setTag(v);
		} else {
			v = (ViewHolder) convertView.getTag();
		}

		v.t1.setText(list.get(position).getLastDateString());
		v.t2.setText(list.get(position).getLessonString());
		v.t3.setText(list.get(position).getStatusString());
		if (list.size() >= 9) {
			if (position == this.position) {

				v.t1.setVisibility(View.VISIBLE);
				// v.t2.setTextColor(Color.WHITE);
			} else {
				v.t1.setVisibility(View.GONE);
			}
		}
		return convertView;
	}

	public void setSelection(int position) {
		this.position = position;
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
	}

}
