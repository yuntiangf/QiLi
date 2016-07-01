package com.example.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.qili.R;
import com.zxing.dao.Ingredent;
import com.zxing.dao.Progress;
import com.zxing.dao.Type;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FoodDetialActivity extends Activity {
	
	private String food_id;
	private String food_name;
	private String foodtip;
	private String food_url;
	private Ingredent ingredent;
	private List<Progress> progress;
	private ImageService imageService;
	
	private ImageView food_image;
	private TextView food_tip_name;
	private TextView food_tip;
	private TextView food_ingredent_name;
	private TextView food_main_ingredent;
	private TextView food_other_ingredent;
	private TextView food_type_name;
	private TextView food_type;
	private TextView progress_name;
	private ListView progress_listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_detail);
		
		imageService = new ImageService();
		food_id = getIntent().getStringExtra("menu_id");
		food_name = getIntent().getStringExtra("menu_name");
		foodtip = getIntent().getStringExtra("menutip");
		food_url = getIntent().getStringExtra("menu_url");
		
		setTitle("关于"+food_name+"的详情做法：");
		
		findView();
		init();
	}

	//初始化控件
	private void findView() {
		food_image = (ImageView) findViewById(R.id.food_image);
		food_tip_name = (TextView) findViewById(R.id.food_tip_name);
		food_tip = (TextView) findViewById(R.id.food_tip);
		food_ingredent_name = (TextView) findViewById(R.id.food_ingredent_name);
		food_main_ingredent = (TextView) findViewById(R.id.food_main_ingredent);
		food_other_ingredent = (TextView) findViewById(R.id.food_other_ingredent);
		food_type_name = (TextView) findViewById(R.id.food_type_name);
		food_type = (TextView) findViewById(R.id.food_type);
		progress_name = (TextView) findViewById(R.id.progress_name);
		progress_listView = (ListView) findViewById(R.id.progress_listView);
	}
	
	//初始化数据
	private void init() {
		String str = HttpUtils.sendPost(PClass.HTTP+PClass.GETINGREDIENTBYMENU_ID, "id="+food_id);
		System.out.println(str);
		
		ingredent = new Ingredent();
		ingredent = JSON.parseObject(str, Ingredent.class);
		String main_ingredent = "";
		String other_ingredent = "";
		main_ingredent = ingredent.getMain_ingredent();
		other_ingredent = ingredent.getOther_ingredent();
		
		
		try {
			food_image.setImageBitmap(imageService.getImage(PClass.HTTP+food_url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		food_tip_name.setText(food_name+"的小贴士：");
		food_tip.setText(foodtip);
		food_ingredent_name.setText(food_name+"的材料组成：");
		food_main_ingredent.setText(main_ingredent);
		food_other_ingredent.setText(other_ingredent);
		food_type_name.setText(food_name+"的功效如下：");
		food_type.setText(getType());
		progress_name.setText(food_name+"的具体做法步骤：");
		
		String url = HttpUtils.sendPost(PClass.HTTP+PClass.GETPROGRESSBYMENU_ID, "id="+food_id);
		System.out.println(url);
		
		progress = new ArrayList<Progress>();
		progress = JSON.parseArray(url, Progress.class);
		
		ProgressAdapter adapter = new ProgressAdapter();
		progress_listView.setAdapter(adapter);
	}

	private String getType() {
		String str = HttpUtils.sendPost(PClass.HTTP+PClass.GETTYPEBYMENU_ID, "id="+food_id);
		System.out.println(str);
		List<Type> type = new ArrayList<Type>();
		type = JSON.parseArray(str, Type.class);
		String typeString="";
		for(Type t :type){
			typeString += t.getName()+"	";
		}
		System.out.println(typeString);
		return typeString;
	}
	
	class ProgressAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return progress.size();
		}

		@Override
		public Object getItem(int position) {
			return progress.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Progress pro = progress.get(position);
			
			View view = View.inflate(FoodDetialActivity.this, R.layout.progress_item, null);
			ImageView image = (ImageView) view.findViewById(R.id.progress_item_image);
			TextView text = (TextView) view.findViewById(R.id.progress_item);
			text.setText(pro.getStep());
			try {
				image.setImageBitmap(imageService.getImage(PClass.HTTP+pro.getImageRelativePath()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}
}
