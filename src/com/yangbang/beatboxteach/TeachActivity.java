package com.yangbang.beatboxteach;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yangbang.beatboxteach.base.BaseActivity;
import com.yangbang.beatboxteach.base.MyApplication;
import com.yangbang.beatboxteach.entity.Teaching;
import com.yangbang.beatboxteach.entity.Voice;
import com.yangbang.beatboxteach.view.TitleBar;

public class TeachActivity extends BaseActivity {
	TitleBar titlebar;
	TextView activity_teach_content_tv;
	TextView activity_teach_use_tv;
	WebView activity_teach_webview;
	ScrollView activity_teach_scroll;
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
		// titlebar.setTitleText(voice.getName());
		activity_teach_webview = (WebView) this
				.findViewById(R.id.activity_teach_webview);
		activity_teach_scroll = (ScrollView) this
				.findViewById(R.id.activity_teach_scroll);
		activity_teach_content_tv = (TextView) this
				.findViewById(R.id.activity_teach_content_tv);
		activity_teach_use_tv = (TextView) this
				.findViewById(R.id.activity_teach_use_tv);
		initData();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initData() {
		Teaching teaching = null;
		try {
			teaching = MyApplication.dbUtils.findFirst(Selector
					.from(Teaching.class)
					.where("titleName", "=", voice.getTitleName())
					.and(WhereBuilder.b("voiceName", "=", voice.getName())));
		} catch (DbException e) {
			e.printStackTrace();
		}
		if (teaching != null) {
			titlebar.setTitleText(teaching.getTidyName());
			Log.i("YANGBANG", "type-->" + teaching.getType() + ",content-->"
					+ teaching.getContent());
			if (teaching.getType() == 1) {// 文字
				activity_teach_content_tv.setText(teaching.getContent()
						.replace("\\n", "\n"));
			} else if (teaching.getType() == 2) {// 视频
				activity_teach_webview.setVisibility(View.VISIBLE);
				activity_teach_scroll.setVisibility(View.GONE);
				activity_teach_webview.loadUrl(teaching.getContent());
				activity_teach_webview.getSettings().setJavaScriptEnabled(true);
				activity_teach_webview.setWebViewClient(new WebViewClient() {
					@Override
					public boolean shouldOverrideUrlLoading(WebView view,
							String url) {
						view.loadUrl(url);
						return true;
						// return super.shouldOverrideUrlLoading(view, url);
					}
				});
				activity_teach_webview
						.setWebChromeClient(new WebChromeClient() {

							@Override
							public void onReceivedTitle(WebView view,
									String title) {
								// 设置标题
								// viewCenter.setText(title);
								super.onReceivedTitle(view, title);
							}

						});
			}
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			activity_teach_webview.onPause(); // 暂停网页中正在播放的视频
		}
	}

	@Override
	protected int getContentViewID() {
		return R.layout.activity_teach;
	}

}
