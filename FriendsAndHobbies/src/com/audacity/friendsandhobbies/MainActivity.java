package com.audacity.friendsandhobbies;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        if(sharedPreferences.getBoolean("signup_status", false) == false) {
        	
        	Intent i = new Intent(this, SignupActivity.class);
            startActivity(i);
        }
        else {
        	
        	Intent i = new Intent(this, FiveSqureActivity.class);
            startActivity(i);
        }
    }
}