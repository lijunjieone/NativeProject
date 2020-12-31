package com.a.news.mp3encode;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	static {
		System.loadLibrary("audioencoder");
	}
	private TextView mp3_encoder_btn;
	private final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mp3_encoder_btn =  (TextView)findViewById(R.id.tv_text);
		mp3_encoder_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Mp3Encoder encoder = new Mp3Encoder();
				String pcmPath = "/mnt/sdcard/a_songstudio/vocal.pcm";
				int audioChannels = 2;
				int bitRate = 128 * 1024;
				int sampleRate = 44100;
				String mp3Path = "/mnt/sdcard/a_songstudio/vocal.mp3";
				int ret = encoder.init(pcmPath, audioChannels, bitRate, sampleRate, mp3Path);
				if(ret >= 0) {
					encoder.encode();
					encoder.destroy();
					Log.i(TAG, "Encode Mp3 Success");
				} else {
					Log.i(TAG, "Encoder Initialized Failed...");
				}
			}
		});
	}


}
