package com.yangbang.beatboxteach.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yangbang.beatboxteach.R;

public class TitleBar extends LinearLayout {
	private ImageView left_img;
	private TextView title_bar_text;
	private ImageView right_img;
	private View titleBarView;

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		titleBarView = LayoutInflater.from(context).inflate(R.layout.title_bar,
				this);
		initView();
	}

	public TitleBar(Context context) {
		super(context);
		titleBarView = LayoutInflater.from(context).inflate(R.layout.title_bar,
				this);
		initView();
	}

	private void initView() {
		left_img = (ImageView) titleBarView.findViewById(R.id.left_img);
		title_bar_text = (TextView) titleBarView
				.findViewById(R.id.title_bar_text);
		right_img = (ImageView) titleBarView.findViewById(R.id.right_img);
	}

	public void setLeftImg(int resId) {
		left_img.setVisibility(View.VISIBLE);
		left_img.setImageResource(resId);
	}

	public void setRightImg(int resId) {
		right_img.setVisibility(View.VISIBLE);
		right_img.setImageResource(resId);
	}

	public void setTitleText(String str) {
		if (str != null) {
			title_bar_text.setVisibility(View.VISIBLE);
			title_bar_text.setText(str);
		}
	}

	public void setLeftListener(OnClickListener listener) {
		if (listener != null) {
			left_img.setVisibility(View.VISIBLE);
			left_img.setOnClickListener(listener);
		} else {
			left_img.setVisibility(View.INVISIBLE);
		}
	}

	public void setRightVisible(int visibility) {
		left_img.setVisibility(visibility);
	}

	public void setRightListener(OnClickListener listener) {
		if (listener != null) {
			right_img.setVisibility(View.VISIBLE);
			right_img.setOnClickListener(listener);
		} else {
			right_img.setVisibility(View.INVISIBLE);
		}
	}

}
