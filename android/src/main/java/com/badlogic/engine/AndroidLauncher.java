package com.badlogic.engine;

import android.os.Bundle;

import com.badlogic.engine.video.ChatEngine;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
//	private VideoEngine engine;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
		initialize(new EngineController(), configuration);
		new ChatEngine(this).startSignal();
//		engine=new VideoEngine(this);
//		engine.requestPermissions(new VideoEngine.PermissionHandler() {
//			@Override
//			public void onPermissionsGranted() {
//				engine.start();
//			}
//
//			@Override
//			public void onPermissionsDenied() {
//
//			}
//		});
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//		engine.onRequestPermissionsResult(requestCode,permissions,grantResults);
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}