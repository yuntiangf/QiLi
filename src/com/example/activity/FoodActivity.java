package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.qili.R;
import com.zxing.dao.Food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FoodActivity extends Activity implements OnItemClickListener {

	private List<Food> foods;
	private ListView food_listView;
	private EditText input_food_name;
	private ImageService imageService;
	private String[] allFood = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectAll().penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());

		// try {
		String str = HttpUtils.sendPost(PClass.HTTP + PClass.GETALLFOOD,
				"page=1&rows=100");
			foods = new ArrayList<Food>();
			foods = JSON.parseArray(str, Food.class);

		// for (int i = 0; i < jsonArray.length(); i++) {
		// JSONObject js = jsonArray.getJSONObject(i);
		// Food food = new Food();
		// allFood[i] = js.getString("name");
		//
		// food.setId(js.getString("id"));
		// food.setName(js.getString("name"));
		// food.setTip(js.getString("tip"));
		// food.setImageUrl(js.getString("imageRelativePath"));
		//
		// foods.add(food);

		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
			
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(str);
			allFood = new String[jsonArray.length()];
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		autoCompletefromAllFood();
		food_listView = (ListView) findViewById(R.id.food_listView);
		FoodAdapter adapter = new FoodAdapter();
		food_listView.setAdapter(adapter);
		food_listView.setOnItemClickListener(this);

	}

	private void autoCompletefromAllFood() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				FoodActivity.this, android.R.layout.simple_list_item_1, allFood);
		AutoCompleteTextView autoText = (AutoCompleteTextView) findViewById(R.id.input_food_name);
		autoText.setAdapter(adapter);
		autoText.setThreshold(1);

	}

	private class FoodAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return foods.size();
		}

		@Override
		public Object getItem(int position) {
			return foods.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Food food = foods.get(position);
			View view = View.inflate(FoodActivity.this, R.layout.food_item,
					null);
			TextView food_name = (TextView) view.findViewById(R.id.food_name);
			ImageView image = (ImageView) view.findViewById(R.id.food_image);
			imageService = new ImageService();
			try {
				image.setImageBitmap(imageService.getImage(PClass.HTTP
						+ food.getImageRelativePath()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			food_name.setText(food.getName());
			return view;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Food food = foods.get(position);
		String food_id = food.getId();
		String food_name = food.getName();
		String food_tip = food.getTip();
		Intent intent = new Intent(FoodActivity.this, MenuActivity.class);
		intent.putExtra("food_id", food_id);
		intent.putExtra("food_name", food_name);
		intent.putExtra("food_tip", food_tip);
		startActivity(intent);
	}
}
