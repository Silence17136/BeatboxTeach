package com.yangbang.beatboxteach;

import android.os.Bundle;
import android.widget.TextView;

import com.yangbang.beatboxteach.base.BaseActivity;
import com.yangbang.beatboxteach.entity.Voice;
import com.yangbang.beatboxteach.view.TitleBar;

public class TeachActivity extends BaseActivity {
	TitleBar titlebar;
	TextView activity_teach_content_tv;
	TextView activity_teach_use_tv;
	Voice voice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initView() {
		voice = (Voice) getIntent().getSerializableExtra("voice");
		titlebar = (TitleBar) this.findViewById(R.id.titleBar_teach);
		titlebar.setLeftImg(R.drawable.back);
		titlebar.setLeftListener(this);
		titlebar.setTitleText(voice.getName());
		activity_teach_content_tv = (TextView) this
				.findViewById(R.id.activity_teach_content_tv);
		activity_teach_use_tv = (TextView) this
				.findViewById(R.id.activity_teach_use_tv);
	}

	@Override
	protected int getContentViewID() {
		return R.layout.activity_teach;
	}

}
