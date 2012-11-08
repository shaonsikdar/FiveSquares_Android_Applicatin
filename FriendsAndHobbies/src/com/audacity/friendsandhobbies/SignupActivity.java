package com.audacity.friendsandhobbies;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.audacity.friendsandhobbies.util.PreferenceUtil;
import com.audacity.friendsandhobbies.util.httpClient;

public class SignupActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	httpClient signupHttp = new httpClient();
	ArrayList<NameValuePair> signupNVP;
	ProgressDialog progressDialog;
	PreferenceUtil preferenceUtil;
	
	String resposne, status ,error, email, hobby;
	
	EditText etName, etSurName,etEmail, etAge, etCountry, etRegion, etCity, etMobile, etHobbies;
	Button btnSignUp;
	Spinner spGender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.signup);
		
		initComponents();
	}
	
	private void initComponents() {
		
		try {
			preferenceUtil = new PreferenceUtil(this);
			
			etName = (EditText) findViewById(R.id.editText_signup_name);
			etSurName = (EditText) findViewById(R.id.editText_signup_surname);
			etEmail = (EditText) findViewById(R.id.editText_signup_email);
			spGender = (Spinner) findViewById(R.id.spinner_signup_gender);
			ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.gender_array, android.R.layout.simple_spinner_item);
			spGender.setAdapter(spinnerAdapter);
			
			etAge = (EditText) findViewById(R.id.editText_signup_age);
			
			etCountry = (EditText) findViewById(R.id.editText_signup_country);
			etRegion = (EditText) findViewById(R.id.editText_signup_region);
			etCity = (EditText) findViewById(R.id.editText_signup_city);
			etMobile = (EditText) findViewById(R.id.editText_signup_mobile);
			etHobbies = (EditText) findViewById(R.id.editText_signup_hobbies);
			
			btnSignUp = (Button) findViewById(R.id.button_signup_signup);
			
			btnSignUp.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
				new SignupTask().execute();
//					Intent i = new Intent(SignupActivity.this, FriendSuggestion.class);
//					startActivity(i);
				}//end of onClick()
				
			});//end of
			
		} catch (Exception e) {

			Log.i("Exception on initComponents:","Exception is:"+e);
			
		}//end of exception handling
		
	}//end of initComponents()
	
	private void signUp() {
		
		try {
			email = etEmail.getText().toString();
			hobby = etHobbies.getText().toString();
			
			signupNVP = new ArrayList<NameValuePair>();
			
			//Below for registration user
			signupNVP.add(new BasicNameValuePair("tag", "register"));
			signupNVP.add(new BasicNameValuePair("name", etName.getText().toString()));
			signupNVP.add(new BasicNameValuePair("surname", etSurName.getText().toString()));
			signupNVP.add(new BasicNameValuePair("email", email));
			signupNVP.add(new BasicNameValuePair("gender", spGender.getSelectedItem().toString()));
			signupNVP.add(new BasicNameValuePair("age", etAge.getText().toString()));
			signupNVP.add(new BasicNameValuePair("country", etCountry.getText().toString()));
			signupNVP.add(new BasicNameValuePair("region", etRegion.getText().toString()));
			signupNVP.add(new BasicNameValuePair("city", etCity.getText().toString()));
			signupNVP.add(new BasicNameValuePair("mobile", etMobile.getText().toString()));
			signupNVP.add(new BasicNameValuePair("hobby", hobby));
			
			// add some more fields....
//			Log.i("Checiking Input Value : " , "name ="+etName.getText().toString() +"surname="+etSurName+"email="+etEmail+"Sex="+spGender.getSelectedItem().toString()+"age="+etAge+"country="+etCountry
//					+"region="+etRegion+"city="+etCity+"mobile="+"hobby"+etHobbies);
			
			
			//Below for finding user by surname
			//Example URL:http://localhost/friendsboxes/fb_json.php?tag=userinfo&surname=Sikdar
            //If success return :
            //{"tag":"userinfo","success":1,"error":0,"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}]}
			
//			signupNVP = new ArrayList<NameValuePair>();
//			signupNVP.add(new BasicNameValuePair("tag", "userinfo"));
//			signupNVP.add(new BasicNameValuePair("surname", etSurName.getText().toString()));
			
			//Done Perfect
			//Response from server:
			//{"tag":"userinfo","success":1,"error":0,"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}]}
			// Should checking user array is greater than one or not
			
			
			//Below for finding user by hobby
			//Example URL: http://localhost/friendsboxes/fb_json.php?tag=user&by=hobby&hobby=think
            //If success return :
            //{"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}],"success":1}
			// if by "ing"
			//"{user":[{"Name":"Kamo","Surname":"Kamo","Email":"dr.kamo@yahoo.com","Gender":"male","Age":"35","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"2147483647","HobbyName":"chating"},{"Name":"Foisal","Surname":"Fusi","Email":"fusi@yahoo.com","Gender":"Male","Age":"21","Country":"Bangladesh","Region":"Dhaka ","City":"Dhaka","Mobile":"1534853989","HobbyName":"Creating Database "},{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"},{"Name":"Maruf","Surname":"Rahman","Email":"maruf@yahoo.com","Gender":"Female","Age":"21","Country":"Bangladesh","Region":"Dhaka ","City":"Dhaka","Mobile":"153323989","HobbyName":"Creating Movie "},{"Name":"Last","Surname":"User","Email":"user@yahoo.com","Gender":"Male","Age":"25","Country":"Bangladesh","Region":"Dhaka","City":"Dhaka","Mobile":"43432491","HobbyName":"Checking"}],"success":1}

//			signupNVP = new ArrayList<NameValuePair>();
//			signupNVP.add(new BasicNameValuePair("tag", "user"));
//			signupNVP.add(new BasicNameValuePair("by", "hobby"));
//			signupNVP.add(new BasicNameValuePair("hobby", etHobbies.getText().toString()));
			//Done perfect
			
			//Below for finding user by email
			//Example URL: http://localhost/friendsboxes/fb_json.php?tag=user&by=email&email=shaon_sikdar@yahoo.c
            //If success return :
            //{"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}],"success":1}

//			signupNVP = new ArrayList<NameValuePair>();
//			signupNVP.add(new BasicNameValuePair("tag", "user"));
//			signupNVP.add(new BasicNameValuePair("by", "email"));
//			signupNVP.add(new BasicNameValuePair("email", etEmail.getText().toString()));
			
			//Done perfect
			
			//Below for finding user by Mobile
			//Example URL: http://localhost/friendsboxes/fb_json.php?tag=user&by=mobile&mobile=167092342
            //If success return :
            //{"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}],"success":1}

//			signupNVP = new ArrayList<NameValuePair>();
//			signupNVP.add(new BasicNameValuePair("tag", "user"));
//			signupNVP.add(new BasicNameValuePair("by", "mobile"));
//			signupNVP.add(new BasicNameValuePair("mobile", etMobile.getText().toString()));
			
			//Done perfect
			
			resposne = signupHttp.httpConnection(signupNVP);
			Log.i("response of json","Here:"+resposne);
			
			parseResponse(resposne);
			
		} catch (Exception e) {
			
			Log.i("Exception on signUp()","Exception is:"+e);
			
		}
	}
	
	private String parseResponse(String jsonString) {
		
		JSONObject jsonObject;
		
		try {

			jsonObject = new JSONObject(jsonString);

			status = jsonObject.getString("success");
			
			if(status.equalsIgnoreCase("1")) {
				
				Log.i("Success Ok","Sending Success Msg");
				return status;
				
			}
			else{
				
				error = jsonObject.getString("error");
				Log.i("Else condition:","Error else");
				return error; 
			} 
			
			
		}
		catch(Exception ex) {
			
			Log.e("Response_Parsing_Error:", ex.getLocalizedMessage());
		}
		
		return null;
	}
	
	
	protected class SignupTask extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			
			progressDialog = ProgressDialog.show(SignupActivity.this, "Signup", "Processing, Please wait...");
			
		}
		
		@Override
		protected String doInBackground(Void... params) {

			try {
			
				signUp();
				
				return status;
			}
			catch(Exception ex) {
				
				
				Log.e("AsyncTask_Error:", ex.getLocalizedMessage());
				
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String msg) {
			
			progressDialog.dismiss();
			
			if(msg.equalsIgnoreCase("1")) {
				
//				Toast.makeText(SignupActivity.this, "Signup successful!", 1000).show();
				preferenceUtil.setUserEmail(email);
				Intent i = new Intent(SignupActivity.this, FriendSuggestion.class);
				i.putExtra("hobby", hobby);
				startActivity(i);
			}
			else {
				
				Toast.makeText(SignupActivity.this, "Signup failed! Please try again...", 1000).show();
			}
		}
	}
}