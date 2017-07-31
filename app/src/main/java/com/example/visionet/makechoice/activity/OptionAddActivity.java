/*
 * Copyright (c) 2017. Visionet and/or its affiliates. All right reserved.
 * VISIONET PROPRIETARY/CONFIDENTIAL.
 */
package com.example.visionet.makechoice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.visionet.makechoice.R;

/**
 * @author TC.Ubuntu
 * @since 2017/7/30.
 */
public class OptionAddActivity extends AppCompatActivity implements View.OnClickListener {

	private Intent intent;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option_layout);
		intent = getIntent();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.newOption_back:
				backListener();
			default:
				break;
		}
	}

	private void backListener() {
//		Bundle bundle = new Bundle();
//		bundle.putString("rs", "我是OptionAddActivity关闭后回传的值！");
//		//将Bundle赋给Intent
//		intent.putExtras(bundle);
		//跳转回MainActivity
		//注意下面的RESULT_OK常量要与回传接收的Activity中onActivityResult（）方法一致
		OptionAddActivity.this.setResult(RESULT_OK, intent);
		//关闭当前activity
		OptionAddActivity.this.finish();
	}
}
