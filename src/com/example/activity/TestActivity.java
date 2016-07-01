package com.example.activity;

import java.util.ArrayList;

import com.example.activity.DataBase.Detection;
import com.example.qili.R;
import com.zxing.dao.Tb_detection;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends DetectionActivity {
	private ListView listView;
	private Button button1,button2;
	private TextView textView;
	private ArrayList<Tb_detection> tb_detection;
	private int index=0;
	private int VIEW_COUNT=10;
	private Dialog dialog;
	private int[] score=new int[67];
	public static final int START=Menu.FIRST;
	public static final int ABOUT=Menu.FIRST+1;
	public static int flag = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		
		showData();
		listView=(ListView) findViewById(R.id.list);
		button1=(Button) findViewById(R.id.button1);
		button2=(Button) findViewById(R.id.button2);
		textView=(TextView) findViewById(R.id.textView);
		button1.setEnabled(false);
		final DetectionAdapter dtAdapter=new DetectionAdapter();
		listView.setAdapter(dtAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final Tb_detection tb=tb_detection.get(position+index*VIEW_COUNT);
				int tb_id=tb.getDetection_id();
				String tb_question=tb.getDetection_question();
				LayoutInflater layoutInflater=LayoutInflater.from(TestActivity.this);
				View v=layoutInflater.inflate(R.layout.dialog, null);
				dialog =new AlertDialog.Builder(TestActivity.this)
						.setTitle("第 "+tb_id+"题，请选择 ").setIcon(R.drawable.ic_launcher)
						.setView(v).create();
				
				dialog.show();
				final TextView choose=(TextView) view.findViewById(R.id.choose);
				TextView question=(TextView) v.findViewById(R.id.question);
				question.setText("	"+tb_id+"、"+tb_question);
				final TextView text1=(TextView) dialog.findViewById(R.id.text1);
				final TextView text2=(TextView) dialog.findViewById(R.id.text2);
				final TextView text3=(TextView) dialog.findViewById(R.id.text3);
				final TextView text4=(TextView) dialog.findViewById(R.id.text4);
				final TextView text5=(TextView) dialog.findViewById(R.id.text5);
				text1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						choose.setText("没有");
						tb.setDetection_choose("没有");
						text1.setBackgroundColor(R.drawable.blue);
						add(tb);
						dialog.dismiss();
					}
				});
				text2.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						choose.setText("很少");
						tb.setDetection_choose("很少");
						text2.setBackgroundColor(R.drawable.blue);
						add(tb);
						dialog.dismiss();
					}
				});
				text3.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						choose.setText("正常");
						tb.setDetection_choose("正常");
						text3.setBackgroundColor(R.drawable.blue);
						add(tb);
						dialog.dismiss();
					}
				});
				text4.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						choose.setText("经常");
						tb.setDetection_choose("经常");
						text4.setBackgroundColor(R.drawable.blue);
						add(tb);
						dialog.dismiss();
					}
				});
				text5.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						choose.setText("总是");
						tb.setDetection_choose("总是");
						text5.setBackgroundColor(R.drawable.blue);
						add(tb);
						dialog.dismiss();
					}
				});
			}
		});
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				index--;
				dtAdapter.notifyDataSetChanged();
				textView.setText("第  "+(index+1)+" 页");
				textView.setTextSize(18);
				changeButtonStatus();
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				index++;
				dtAdapter.notifyDataSetChanged();
				textView.setText("第  "+(index+1)+" 页");
				textView.setTextSize(18);
				changeButtonStatus();
			}
		});
		
	}
	
	public void changeButtonStatus(){
		if(index<=0){
			button1.setEnabled(false);
		}else if(tb_detection.size()-index*VIEW_COUNT<=VIEW_COUNT){
			button2.setEnabled(false);
		}else{
			button1.setEnabled(true);
			button2.setEnabled(true);
		}
	}
	
	private void add(Tb_detection tb){
		int id=tb.getDetection_id();
		int type=tb.getDetection_type();
		String choose=tb.getDetection_choose();
		if(type==0){
			if(choose.equals("没有")){
				score[id - 1] = 1;
			}else if(choose.equals("很少")){
				score[id - 1] = 2;
			}else if(choose.equals("正常")){
				score[id - 1] = 3;
			}else if(choose.equals("经常")){
				score[id - 1] = 4;
			}else if(choose.equals("总是")){
				score[id - 1] = 5;
			}
		}else if(type==1){
			if(choose.equals("没有")){
				score[id - 1] = 5;
			}else if(choose.equals("很少")){
				score[id - 1] = 4;
			}else if(choose.equals("正常")){
				score[id - 1] = 3;
			}else if(choose.equals("经常")){
				score[id - 1] = 2;
			}else if(choose.equals("总是")){
				score[id - 1] = 1;
			}
		}
	}
	
	public void showData(){
		mCursor=mDB.query(Detection.Detection_TABLE_NAME, new String[]{
				Detection.Detection_ID,Detection.Detection_QUESTION,Detection.Detection_TYPE,
				Detection.Detection_HABITUS,Detection.Detection_CHOOSE},
				null, null, null, null, null);
		int num=mCursor.getCount();
		tb_detection=new ArrayList<Tb_detection>();
		if(num >0 && (mCursor.moveToFirst())){
			for(int i =0; i < num; i++){
				Tb_detection tb=new Tb_detection();
				tb.setDetection_id(mCursor.getInt(mCursor.getColumnIndex
						(Detection.Detection_ID)));
				tb.setDetection_question(mCursor.getString(mCursor.getColumnIndex
						(Detection.Detection_QUESTION)));
				tb.setDetection_type(mCursor.getInt(mCursor.getColumnIndex
						(Detection.Detection_TYPE)));
				tb.setDetection_habitus(mCursor.getString(mCursor.getColumnIndex
						(Detection.Detection_HABITUS)));
				tb.setDetection_choose(mCursor.getString(mCursor.getColumnIndex
						(Detection.Detection_CHOOSE)));
				tb_detection.add(tb);
				mCursor.moveToNext();
			}
		}
	}
	
	public class DetectionAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			int num=tb_detection.size();
			if(num-index*VIEW_COUNT<VIEW_COUNT){
				return num-index*VIEW_COUNT;
			}else{
				return VIEW_COUNT;
			}
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Tb_detection tb=tb_detection.get(position+index*VIEW_COUNT);
			View view=View.inflate(TestActivity.this, R.layout.question_item, null);
			TextView question=(TextView) view.findViewById(R.id.question_item);
			TextView choose=(TextView) view.findViewById(R.id.choose);
			String str=tb.getDetection_question();
			if(str.length() > 12){
				str=str.substring(0, 11);
				question.setText(str+"...");
			}else{
				question.setText(str); 
			}
			choose.setText(tb.getDetection_choose());
			
			return view;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, START, 0, "start").setIcon(android.R.drawable.ic_menu_edit);
		menu.add(0, ABOUT, 1, "about").setIcon(android.R.drawable.ic_menu_help);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case START:
			double [] data=new double [9];
			data=calculate(score);
			for(int i=0 ; i < score.length; i++){
				if(score[i]==0){
					flag = 1;
					break;
				}
			}
			Intent intent=new Intent(TestActivity.this,ResultActivity.class);
			intent.putExtra("data", data);
			
			if(flag==0){
				startActivity(intent);
			}else if(flag==1){
				Toast.makeText(TestActivity.this, "您未选择完所有题目，得不出正确的结论，请继续选择！",
						Toast.LENGTH_SHORT).show();
				flag=0;
			}
			
			break;
		case ABOUT:
			
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public double[] calculate(int[] aa){
		int [] habitus =new int[9];
		double [] goal=new double[9];
		for(int i=0;i<aa.length;i++){
			if(i>=0 && i<7){
				habitus[0] += aa[i];
			}else if(i>=7 && i<15){
				habitus[1] += aa[i];
			}else if(i>=15 && i<23){
				habitus[2] += aa[i];
			}else if(i>=23 && i<31){
				habitus[3] += aa[i];
			}else if(i>=31 && i<38){
				habitus[4] += aa[i];
			}else if(i>=38 && i<45){
				habitus[5] += aa[i];
			}else if(i>=45 && i<52){
				habitus[6] += aa[i];
			}else if(i>=52 && i<59){
				habitus[7] += aa[i];
			}else if(i>=59 && i<67){
				habitus[8] += aa[i];
			}
		}
		System.out.println("每项得分"+habitus[0]+" , "+habitus[1]+" , "+habitus[2]+" , "
						+habitus[3]+" , "+habitus[4]+" , "+habitus[5]+" , "
						+habitus[6]+" , "+habitus[7]+" , "+habitus[8]);
		
		goal[0] = transform(habitus[0], 7);
		goal[1] = transform(habitus[1], 8);
		goal[2] = transform(habitus[2], 8);
		goal[3] = transform(habitus[3], 8);
		goal[4] = transform(habitus[4], 7);
		goal[5] = transform(habitus[5], 7);
		goal[6] = transform(habitus[6], 7);
		goal[7] = transform(habitus[7], 7);
		goal[8] = transform(habitus[8], 8);
		
		System.out.println("转化后分数：" +goal[0]+","+goal[1]+","+goal[2]+","+goal[3]+","
				+goal[4]+","+goal[5]+","+goal[6]+","+goal[7]+","+goal[8]);
		return goal;
	}
	
	private double transform(int a,int b){
		double s1= a - b;
		double s2= b * 4;
		double s3= s1 / s2;
		double s4= s3 * 100;
		return s4;
	}
}
