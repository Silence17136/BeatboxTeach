package com.yangbang.beatboxteach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.yangbang.beatboxteach.adapter.VoiceAdapter;
import com.yangbang.beatboxteach.base.BaseActivity;
import com.yangbang.beatboxteach.base.MyApplication;
import com.yangbang.beatboxteach.entity.Title;
import com.yangbang.beatboxteach.entity.Voice;
import com.yangbang.beatboxteach.view.TitleBar;

public class VoiceActivity extends BaseActivity implements OnClickListener {
	TitleBar titlebar_voice;
	ListView activity_voice_lv;
	Title title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initView() {
		title = (Title) getIntent().getSerializableExtra("title");
		titlebar_voice = (TitleBar) this.findViewById(R.id.titlebar_voice);
		titlebar_voice.setTitleText(title.getName());
		titlebar_voice.setLeftImg(R.drawable.back);
		titlebar_voice.setLeftListener(this);
		activity_voice_lv = (ListView) this
				.findViewById(R.id.activity_voice_lv);
		Class<Voice> class1 = Voice.class;
		Map<String, String> whereMap = new HashMap<String, String>();
		whereMap.put("title_id", title.getTitle_id() + "");
		List<Voice> dataList = MyApplication.dataHelper.getObjectList(
				class1.getName(), whereMap);
		activity_voice_lv.setAdapter(new VoiceAdapter(this, dataList));
	}

	@Override
	protected int getContentViewID() {
		return R.layout.activity_voice;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			onBackPressed();
			break;

		default:
			break;
		}
	}

}
