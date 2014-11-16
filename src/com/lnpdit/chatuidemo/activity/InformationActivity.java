package com.lnpdit.chatuidemo.activity;

import com.lnpdit.chatuidemo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class InformationActivity extends Activity implements OnClickListener {

	Button return_bt;

	Button fengcai_bt;
	Button peixun_bt;
	Button jiaoliu_bt;
	Button tupian_bt;
	Button shipin_bt;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		viewInit();
	}

	private void viewInit() {
		return_bt = (Button)findViewById(R.id.return_bt);
		return_bt.setOnClickListener(this);
		return_bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		fengcai_bt = (Button) findViewById(R.id.yuangongfengcai_bt);
		peixun_bt = (Button) findViewById(R.id.yuangongpeixun_bt);
		jiaoliu_bt = (Button) findViewById(R.id.jiaoliu_bt);
		tupian_bt = (Button) findViewById(R.id.tupian_bt);
		shipin_bt = (Button) findViewById(R.id.shipin_bt);

		fengcai_bt.setClickable(true);
		peixun_bt.setClickable(true);
		jiaoliu_bt.setClickable(true);
		tupian_bt.setClickable(true);
		shipin_bt.setClickable(true);

		fengcai_bt.setOnClickListener(this);
		peixun_bt.setOnClickListener(this);
		jiaoliu_bt.setOnClickListener(this);
		tupian_bt.setOnClickListener(this);
		shipin_bt.setOnClickListener(this);
	}

	public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.yuangongfengcai_bt:
				startActivity(new Intent(InformationActivity.this, NewsActivity.class));
				break;
			case R.id.yuangongpeixun_bt:
				startActivity(new Intent(InformationActivity.this, PeixunQuestionActivity.class));
				break;
//			case R.id.xinxijiaoliu_bt:
//				try {
//					Intent intent = new Intent();
//					intent.setClassName("com.lnpdit.chatuidemo",
//							"com.lnpdit.chatuidemo.activity.LoginActivity");
//					intent.putExtra("username", "wys");
//					intent.putExtra("pwd", "111111");
//					intent.putExtra("name", "王元石");
//					startActivity(intent);
//				} catch (Exception e) {
//					// TODO: handle exception
//					Toast.makeText(getApplicationContext(), "未安装一点通交流系统", Toast.LENGTH_SHORT).show();
////					UpdateManager uManager = new UpdateManager(InformationActivity.this);
////					uManager.downloadmeeting();
//				}
//				break;
			case R.id.tupian_bt:
				startActivity(new Intent(InformationActivity.this, PicActivity.class));
				break;
			case R.id.shipin_bt:
				startActivity(new Intent(InformationActivity.this, VideoActivity.class));
				break;

			default:
				break;
			}
	}
}