package com.audacity.friendsandhobbies;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.audacity.friendsandhobbies.util.PreferenceUtil;
import com.audacity.friendsandhobbies.util.httpClient;

public class FriendSuggestion extends Activity {
	
	httpClient http = new httpClient();
	ArrayList<NameValuePair> friendSearchNVP;
	ProgressDialog progressDialog;
	PreferenceUtil preferenceUtil;
	
	String hobby, resposne;

	GridView gvGrid;
	
	String[] friends = {"Shaon", "Siddiq", "Faisal", "Zahid", "Tuhin", "Nayan", "Sajib", "Soton"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_suggestion);
		
		hobby = getIntent().getStringExtra("hobby");
		
		preferenceUtil = new PreferenceUtil(this);
		
		gvGrid = (GridView) findViewById(R.id.gridView_friendSuggestion);
		gvGrid.setAdapter(new GridAdapter(this, friends));
		gvGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(FriendSuggestion.this, "Friend " + arg0.getItemAtPosition(arg2) + " selected.", 1000).show();
			}
		});
	} 
	
	private class GridAdapter extends BaseAdapter {
		
		private Context mContext;
		private String[] mFriends;
		private LayoutInflater inflater;
		
		public GridAdapter(Context mContext, String[] mFriends) {
			super();
			this.mContext = mContext;
			this.mFriends = mFriends;
			this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mFriends.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return mFriends[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View gridView;
			
			if(convertView == null) {
				
//				gridView = new View(mContext);
				gridView = inflater.inflate(R.layout.grid_item, null );
				
				// set image based on selected text
				ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
				imageView.setImageResource(R.drawable.ic_launcher);
				
				// set text
				TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
				textView.setText(mFriends[position]);
	 
			}
			else {
				
				gridView = convertView;
			}
			
			return gridView;
		}
	}
	
	private class FriendSearch extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(FriendSuggestion.this, "Loading", "Searching friends, please wait...");
		}
		
		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
//			String email = preferenceUtil.getUserEmail();
//			
//			friendSearchNVP = new ArrayList<NameValuePair>();
//			friendSearchNVP.add(new BasicNameValuePair("tag", "user"));
//			friendSearchNVP.add(new BasicNameValuePair("by", "hobby"));
//			friendSearchNVP.add(new BasicNameValuePair("hobby", hobby));
//			
//			resposne = signupHttp.httpConnection(signupNVP);
//			Log.i("response of json","Here:"+resposne);
//			
//			parseResponse(resposne);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String msg) {
			progressDialog.dismiss();
		}
	}
	
	private void friendSearch() {
		
		String email = preferenceUtil.getUserEmail();
		
		friendSearchNVP = new ArrayList<NameValuePair>();
		friendSearchNVP.add(new BasicNameValuePair("tag", "user"));
		friendSearchNVP.add(new BasicNameValuePair("by", "hobby"));
		friendSearchNVP.add(new BasicNameValuePair("hobby", hobby));
		
		resposne = http.httpConnection(friendSearchNVP);
		Log.i("response of json","Here:" + resposne);
		
		parseResponse(resposne);
	}
	
	private void parseResponse(String response) {
		
		
	}
}
