package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.qili.R;
import com.zxing.dao.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnItemClickListener{
	
	private List<Menu> menus;
	private ImageService imageService;
	private String food_name;
	private String food_id;
	private String foodtip;
	private TextView food_tip_name;
	private TextView food_tip;
	private TextView menu_name;
	private ListView menu_listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
			.detectAll().penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
			.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
			.penaltyLog().penaltyDeath().build());
		
		food_name = getIntent().getStringExtra("food_name");
		foodtip = getIntent().getStringExtra("food_tip");
		food_id = getIntent().getStringExtra("food_id");
		setTitle(food_name+"的做法如下：");
	
		findView();
		
		String str = HttpUtils.sendPost(PClass.HTTP+PClass.GETMENUBYFOOD_ID, "id="+food_id);
		System.out.println(str);
		
		menus = new ArrayList<Menu>();
		menus = JSON.parseArray(str, Menu.class);
		
		MenuAdapter adapter = new MenuAdapter();
		menu_listView.setAdapter(adapter);
		menu_listView.setOnItemClickListener(this);
		
	}
	
	private void findView(){
		food_tip_name = (TextView) findViewById(R.id.food_tip_name);
		food_tip = (TextView) findViewById(R.id.food_tip);
		menu_name = (TextView) findViewById(R.id.menus);
		menu_listView = (ListView) findViewById(R.id.menu_listview);
		food_tip_name.setText(food_name+"的小贴士");
		food_tip.setText(foodtip);
		menu_name.setText(food_name+"的各种做法：");
	}
	
	class MenuAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return menus.size();
		}

		@Override
		public Object getItem(int position) {
			return menus.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Menu menu = menus.get(position);
			View view  = View.inflate(MenuActivity.this, R.layout.food_item, null);
			ImageView image = (ImageView) view.findViewById(R.id.food_image);
			TextView menu_food_name = (TextView) view.findViewById(R.id.food_name);
			imageService = new ImageService();
			
			try {
				image.setImageBitmap(imageService.getImage(PClass.HTTP+menu.getImageRelativePath()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menu_food_name.setText(menu.getName());
			return view;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Menu menu = menus.get(position);
		String menu_id = menu.getId();
		String menu_name = menu.getName();
		String menutip = menu.getTip();
		String menu_url = menu.getImageRelativePath();
		Intent intent = new Intent(MenuActivity.this,FoodDetialActivity.class);
		intent.putExtra("menu_id", menu_id);
		intent.putExtra("menu_name", menu_name);
		intent.putExtra("menutip", menutip);
		intent.putExtra("menu_url", menu_url);
		startActivity(intent);
	}
}
