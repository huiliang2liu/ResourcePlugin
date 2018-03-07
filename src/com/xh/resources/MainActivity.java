package com.xh.resources;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;

public class MainActivity extends Activity {
	private String packageName = "com.tv.demo";
	private IResources mIResources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			List<String> resourcesApkPaths = new ArrayList<String>();
			resourcesApkPaths.add(getExternalFilesDir("QPP.apk")
					.getAbsolutePath());
			ResourcesManager mResourcesManager = new ResourcesManager(this,
					resourcesApkPaths);
			mIResources = mResourcesManager.package2Resources(packageName);
			setContentView(mIResources.layout("activity_main"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Resources getResources() {
		// TODO Auto-generated method stub
		return mIResources == null ? super.getResources() : mIResources
				.getResources();
	}

	@Override
	public Theme getTheme() {
		// TODO Auto-generated method stub
		return mIResources == null ? super.getTheme() : mIResources.getTheme();
	}

	@Override
	public AssetManager getAssets() {
		// TODO Auto-generated method stub
		return mIResources == null ? super.getAssets() : mIResources
				.getAssetManager();
	}
}
