package com.yangbang.beatboxteach.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends WebView {

	@SuppressLint("SetJavaScriptEnabled")
	@SuppressWarnings("deprecation")
	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WebSettings settings = this.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setPluginState(PluginState.ON);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setAllowFileAccess(true);
		settings.setDefaultTextEncodingName("UTF-8");
		settings.setLoadWithOverviewMode(true);
		// settings.setBuiltInZoomControls(true);
		settings.setUseWideViewPort(true);
		settings.setDomStorageEnabled(true);
		// 应用可以有缓存
		// settings.setAppCacheEnabled(true);
		this.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
				// return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}

}
