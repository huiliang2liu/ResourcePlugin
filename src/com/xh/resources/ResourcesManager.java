package com.xh.resources;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Resources com.xh.resources 2018 2018-3-7 下午12:25:03 instructions：
 * author:liuhuiliang email:825378291@qq.com
 **/

public class ResourcesManager {
	List<IResources> mIResources;

	@SuppressLint("NewApi")
	public ResourcesManager(Context context, List<String> resourcesApkPaths) {
		// TODO Auto-generated constructor stub
		if (context == null)
			throw new RuntimeException("you context is null");
		if (resourcesApkPaths == null || resourcesApkPaths.size() <= 0)
			throw new RuntimeException("you resourcesApkPaths is empty");
		mIResources = new ArrayList<IResources>();
		for (int i = 0; i < resourcesApkPaths.size(); i++) {
			String apkPath = resourcesApkPaths.get(i);
			if (apkPath == null || apkPath.isEmpty()
					|| !apkPath.endsWith(".apk"))
				continue;
			mIResources.add(new ResourcesImpl(context, apkPath));
		}

	}

	public IResources package2Resources(String packageName) {
		for (int i = 0; i < mIResources.size(); i++) {
			IResources resources = mIResources.get(i);
			if (resources.equals(packageName))
				return resources;
		}
		return null;
	}
}
