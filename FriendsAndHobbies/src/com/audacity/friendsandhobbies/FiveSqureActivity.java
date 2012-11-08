package com.audacity.friendsandhobbies;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FiveSqureActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	LinearLayout llFragment;
	RelativeLayout ivAudio, ivVideo, ivPicture, ivDialer, ivInternet;
	
	private final int REQUEST_CODE_AUDIO = 101;
	private final int REQUEST_CODE_VIDEO = 102;
	private final int REQUEST_CODE_PICTURE = 103;
	private final int REQUEST_CODE_DIALER = 104;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_squre);
        
        initComponents();
    }
    
    private void initComponents() {
    	
    	ivAudio = (RelativeLayout) findViewById(R.id.imageView_main_audio);
    	ivAudio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "Audio Fragment!", 1000).show();
				startAudioIntent();
			}
		});
    	
    	ivVideo = (RelativeLayout) findViewById(R.id.imageView_main_video);
    	ivVideo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "Video Fragment!", 1000).show();
				startVideoIntent();
			}
		});
    	
    	ivPicture = (RelativeLayout) findViewById(R.id.imageView_main_picture);
    	ivPicture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "Picture Fragment!", 1000).show();
				startPictureIntent();
			}
		});
    	
    	ivDialer = (RelativeLayout) findViewById(R.id.imageView_main_dialer);
    	ivDialer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "Dialer Fragment!", 1000).show();
				startDialerIntent();
			}
		});
    	
    	ivInternet = (RelativeLayout) findViewById(R.id.linearLayout_fragment);
    	ivInternet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startBrowserIntent();
			}
		});
    }
    
    private void startAudioIntent() {
    	
    	Intent i = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
    	startActivity(i);
    }
    
    private void startVideoIntent() {
    	
    	Intent i = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
    	startActivity(i);
    }
    
    public void startPictureIntent() {
    	
    	Intent i = new Intent(Intent.ACTION_PICK);
    	i.setType("image/*");
    	startActivity(i);
    }
    
    public void startDialerIntent() {
    	
    	Intent i = new Intent(Intent.ACTION_DIAL);
    	startActivity(i);
    }
    
    public void startBrowserIntent() {
    	
    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
    	startActivity(i);
    }
}
