/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.sharesdk.analysis.example;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.sharesdk.analysis.MobclickAgent;

public class TestActivity1 extends Activity implements OnClickListener{
	
	Context context;
	Button btn_event_begin;
	Button btn_event_end;
	Button btn_event_begin_label;
	Button btn_event_end_label;
	Button btn_error;
	Button btn_to_activity;
	TextView tv_show;

	//event id
	final String event_begin_duration_id = "begin_duration_";
	final String event_begin_duration_label_id = "begin_duration_label_";
	
	ArrayList<String> eventBegin = new ArrayList<String>();
	ArrayList<String> eventBeginLabel = new ArrayList<String>(); 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharesdk_example_activity_test);
        
        initView();
        initData();
      
    }

    private void initView(){
    	context = TestActivity1.this;
    	btn_event_begin = (Button) findViewById(R.id.btn_event_begin);
    	btn_event_end = (Button) findViewById(R.id.btn_event_end);
    	btn_event_begin_label = (Button) findViewById(R.id.btn_event_begin_label);
    	btn_event_end_label = (Button) findViewById(R.id.btn_event_end_label);
    	btn_error = (Button) findViewById(R.id.btn_error);
    	btn_to_activity = (Button) findViewById(R.id.btn_to_activity);
    	tv_show = (TextView)findViewById(R.id.tv_show);
    	
    	btn_event_begin.setOnClickListener(this);
    	btn_event_end.setOnClickListener(this);
    	btn_event_begin_label.setOnClickListener(this);
    	btn_event_end_label.setOnClickListener(this);
    	btn_error.setOnClickListener(this);
    	btn_to_activity.setOnClickListener(this);
    	tv_show.setText("当前页面：TestActivity1 "+this.getClass().getName());
    }
    
    private void initData(){}
    
    @Override
    public void onResume(){
    	super.onResume();
    	MobclickAgent.onResume(this);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	MobclickAgent.onPause(this);
    }

	@Override
	public void onClick(View v) {
//		String event;
		switch (v.getId()) {
			case R.id.btn_event_begin:
				MobclickAgent.onEventBegin(context, "event_begin_watch_tv");
				break;
			case R.id.btn_event_end:
					MobclickAgent.onEventEnd(context, "event_begin_watch_tv");
				break;
			case R.id.btn_event_begin_label:
				MobclickAgent.onEventBegin(context, "event_begin_watch_tv_time", "am 10:15");
				break;
			case R.id.btn_event_end_label:
				MobclickAgent.onEventEnd(context, "event_begin_watch_tv_time", "pm 10:15");
				break;			
			case R.id.btn_error:
				MobclickAgent.onError(context, new Throwable("API calls from the server require an appsecret_proof argument").toString());
				break;
			case R.id.btn_to_activity:
				Intent i = new Intent(TestActivity1.this, TestActivity2.class);
				startActivity(i);
				this.finish();
				break;
			default:
				break;
		}
		
	}
    
}
