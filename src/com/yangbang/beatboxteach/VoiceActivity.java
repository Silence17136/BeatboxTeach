package com.yangbang.beatboxteach;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yangbang.beatboxteach.adapter.VoiceAdapter;
import com.yangbang.beatboxteach.base.BaseActivity;
import com.yangbang.beatboxteach.base.MyApplication;
import com.yangbang.beatboxteach.entity.Title;
import com.yangbang.beatboxteach.entity.Voice;
import com.yangbang.beatboxteach.view.TitleBar;

public class VoiceActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {
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
		Log.i("YANGBANGA********", "title-->" + title.toString());
		titlebar_voice = (TitleBar) this.findViewById(R.id.titlebar_voice);
		titlebar_voice.setTitleText(title.getName());
		titlebar_voice.setLeftImg(R.drawable.back);
		titlebar_voice.setLeftListener(this);
		activity_voice_lv = (ListView) this
				.findViewById(R.id.activity_voice_lv);
		// Class<Voice> class1 = Voice.class;
		// Map<String, String> whereMap = new HashMap<String, String>();
		// whereMap.put("title_id", title.getTitle_id() + "");
		// List<Voice> dataList = MyApplication.dataHelper.getObjectList(
		// class1.getName(), whereMap);
		List<Voice> dataList = null;
		try {
			dataList = MyApplication.dbUtils.findAll(Selector.from(Voice.class)
					.where("titleId", "=", title.getId()));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activity_voice_lv.setAdapter(new VoiceAdapter(this, dataList));
		activity_voice_lv.setOnItemClickListener(this);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		VoiceAdapter adapter = (VoiceAdapter) parent.getAdapter();
		Voice voice = (Voice) adapter.getItem(position);
		Intent intent = new Intent(this, TeachActivity.class);
		intent.putExtra("voice", voice);
		startActivity(intent);
	}
}
