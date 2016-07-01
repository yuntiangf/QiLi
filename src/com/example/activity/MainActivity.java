package com.example.activity;

import com.example.qili.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageView mImage;
	private AnimationDrawable animation;
	private Button button1,button2,button3,button4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mImage=(ImageView) findViewById(R.id.imageView01);
		button1=(Button) findViewById(R.id.scan_qrcode);
		button2=(Button) findViewById(R.id.bt_cookmethod);
		button3=(Button) findViewById(R.id.bt_analysis);
		button4=(Button) findViewById(R.id.diet);
		
		mImage.setImageResource(R.anim.aaa);
		animation=(AnimationDrawable) mImage.getDrawable();
		animation.start();
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openCameraIntent =new Intent(MainActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,FoodActivity.class);
				startActivity(intent);
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,DMainActivity.class);
				startActivity(intent);		
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle bundle =data.getExtras();
			String scanResult =bundle.getString("result");
			
			LayoutInflater layoutInflater =LayoutInflater.from(MainActivity.this);
			View myInflater = layoutInflater.inflate(R.layout.show, null);
			TextView result =(TextView) myInflater.findViewById(R.id.showResult);
			result.setText(scanResult);
			
			Dialog dialog =new AlertDialog.Builder(MainActivity.this).setIcon(R.drawable.ic_launcher)
					.setTitle("É¨Ãè½á¹û").setView(myInflater).create();
			dialog.show();
		}
	}

	

}
