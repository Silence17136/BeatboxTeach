package com.yangbang.beatboxteach;

import android.app.Activity;
import android.os.Bundle;

import com.yangbang.beatboxteach.util.InsertData;
import com.yangbang.beatboxteach.view.RoundProgressBar;

public class WelcomeActivity extends Activity {
	RoundProgressBar roundProBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.round_progressbar);
		roundProBar = (RoundProgressBar) this
				.findViewById(R.id.roundProgressBar2);
		initData();
	}

	private void initData() {
		InsertData.insertAllData(this, roundProBar);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}

}
