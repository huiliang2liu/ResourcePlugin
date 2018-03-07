package com.xh.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Resources com.xh.resources 2018 2018-3-7 下午3:51:24 instructions：
 * author:liuhuiliang email:825378291@qq.com
 **/

public class InitializeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new Thread() {
			public void run() {
				try {
					FileOutputStream fis = new FileOutputStream(new File(
							InitializeActivity.this.getExternalFilesDir(null),
							"QPP.apk"));
					InputStream is = getAssets().open("QPP.apk");
					byte[] buff = new byte[1024 * 1024];
					int len = -1;
					while ((len = is.read(buff)) > 0) {
						fis.write(buff, 0, len);
					}
					fis.flush();
					fis.close();
					is.close();
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							startActivity(new Intent(InitializeActivity.this,
									MainActivity.class));
						}
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};
		}.start();
	}
}
