package com.yangbang.beatboxteach;

import android.os.Bundle;

import com.yangbang.beatboxteach.base.BaseActivity;
import com.yangbang.beatboxteach.view.TitleBar;

public class AboutActivity extends BaseActivity {
	TitleBar titlebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initView() {
		titlebar = (TitleBar) this.findViewById(R.id.titlebar_about);
		titlebar.setTitleText("关于");
		titlebar.setLeftImg(R.drawable.back);
		titlebar.setLeftListener(this);
	}

	@Override
	protected int getContentViewID() {
		return R.layout.activity_about;
	}

}
