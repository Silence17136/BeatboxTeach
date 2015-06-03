package com.yangbang.beatboxteach.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yangbang.beatboxteach.R;

public class TitleBar extends LinearLayout implements OnClickListener {
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

	public void setLeftListener(final Activity activity) {
		if (activity != null) {
			left_img.setVisibility(View.VISIBLE);
			left_img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					activity.onBackPressed();
				}
			});
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
