package com.audacity.friendsandhobbies.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtil {

	Context mContext;
	SharedPreferences mSharedPreferences;
	SharedPreferences.Editor mEditor;
	
	public PreferenceUtil(Context mContext) {
		super();
		this.mContext = mContext;
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
	}
	
	public void setUserEmail(String email) {
		
		mEditor = mSharedPreferences.edit();
		mEditor.putString("email", email);
		mEditor.commit();
	}
	
	public String getUserEmail() {
		
		return mSharedPreferences.getString("email", "");
	}
} 
