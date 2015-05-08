package com.yangbang.beatboxteach.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangbang.beatboxteach.R;
import com.yangbang.beatboxteach.entity.Title;

public class TextTeachAdapter extends AbsBaseAdapter<Title> {
	Context context;

	public TextTeachAdapter(Context context, List<Title> dataList) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.fragment_text_teach_listitem, null);
		}
		ImageView imageView = ViewHolders.get(convertView,
				R.id.fragment_text_teach_listitem_img);
		TextView textView = ViewHolders.get(convertView,
				R.id.fragment_text_teach_listitem_tv);
		textView.setText(dataList.get(position).getName());
		return convertView;
	}

}
