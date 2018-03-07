package com.xh.resources;

import java.lang.reflect.Method;

import org.xmlpull.v1.XmlPullParser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Resources com.xh.resources 2018 2018-3-7 下午12:21:53 instructions：
 * author:liuhuiliang email:825378291@qq.com
 **/

public class ResourcesImpl implements IResources {
	private AssetManager mAssetManager;
	private Resources mResources;
	private String mPackageName;
	private Theme mTheme;
	private Context mContext;

	public ResourcesImpl(Context context, String apkPath) {
		// TODO Auto-generated constructor stub
		try {
			mAssetManager = AssetManager.class.newInstance();
			Method addAssetPath = mAssetManager.getClass().getMethod(
					"addAssetPath", String.class);
			addAssetPath.invoke(mAssetManager, apkPath);
			Resources superRes = context.getResources();
			mResources = new Resources(mAssetManager,
					superRes.getDisplayMetrics(), superRes.getConfiguration());
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageArchiveInfo(
							apkPath,
							PackageManager.GET_ACTIVITIES
									| PackageManager.GET_SERVICES
									| PackageManager.GET_META_DATA
									| PackageManager.GET_PERMISSIONS
									| PackageManager.GET_SIGNATURES);
			mPackageName = packageInfo.packageName;
			mTheme = mResources.newTheme();
			mContext = context;
			// Finals适配三星以及部分加载XML出现异常BUG
			try {
				mTheme.applyStyle(packageInfo.applicationInfo.theme, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mTheme.setTo(context.getTheme());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public AssetManager getAssetManager() {
		// TODO Auto-generated method stub
		return mAssetManager;
	}

	@Override
	public Resources getResources() {
		// TODO Auto-generated method stub
		return mResources;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o == null)
			return false;
		if (!ResourcesImpl.class.isAssignableFrom(o.getClass()))
			return false;
		ResourcesImpl resourcesImpl = (ResourcesImpl) o;
		if (mPackageName.equals(resourcesImpl.mPackageName))
			return true;
		return false;
	}

	public boolean equals(String packageName) {
		return mPackageName.equals(packageName);
	}

	@Override
	public int name2id(String name, String type) {
		return mResources.getIdentifier(name, type, mPackageName);
	}

	@Override
	public String packageName() {
		// TODO Auto-generated method stub
		return mPackageName;
	}

	@Override
	public int style(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "style");
	}

	@Override
	public int dimen(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "dimen");
	}

	@Override
	public int color(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "color");
	}

	@Override
	public int colorValue(String name) {
		// TODO Auto-generated method stub
		return mResources.getColor(color(name), mTheme);
	}

	@Override
	public ColorStateList colorList(String name) {
		// TODO Auto-generated method stub
		return mResources.getColorStateList(color(name), mTheme);
	}

	@Override
	public int colorValue(int colorId) {
		// TODO Auto-generated method stub
		return mResources.getColor(colorId, mTheme);
	}

	@Override
	public ColorStateList colorList(int colorId) {
		// TODO Auto-generated method stub
		return mResources.getColorStateList(colorId, mTheme);
	}

	@Override
	public int anim(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "anim");
	}

	@Override
	public int raw(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "raw");
	}

	@Override
	public int attr(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "attr");
	}

	@Override
	public int string(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "string");
	}

	@Override
	public String stringValue(String name) {
		// TODO Auto-generated method stub
		return mResources.getString(string(name));
	}

	@Override
	public String[] stringValues(String name) {
		// TODO Auto-generated method stub
		return mResources.getStringArray(string(name));
	}

	@Override
	public String stringValue(int stringId) {
		// TODO Auto-generated method stub
		return mResources.getString(stringId);
	}

	@Override
	public String[] stringValues(int stringId) {
		// TODO Auto-generated method stub
		return mResources.getStringArray(stringId);
	}

	@Override
	public int id(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "id");
	}

	@Override
	public int drawable(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "drawable");
	}

	@SuppressLint("NewApi")
	@Override
	public Drawable drawableValue(String name) {
		// TODO Auto-generated method stub
		return mResources.getDrawable(drawable(name), mTheme);
	}

	@SuppressLint("NewApi")
	@Override
	public Drawable drawableValue(int drawavleId) {
		// TODO Auto-generated method stub
		return mResources.getDrawable(drawavleId, mTheme);
	}

	@Override
	public int layout(String name) {
		// TODO Auto-generated method stub
		return name2id(name, "layout");
	}

	@Override
	public View layoutValue(String name) {
		// TODO Auto-generated method stub
		return xmlPullParser2view(mResources.getLayout(layout(name)));
	}

	@Override
	public View layoutValue(int layoutId) {
		// TODO Auto-generated method stub
		return xmlPullParser2view(mResources.getLayout(layoutId));
	}

	@Override
	public Theme getTheme() {
		// TODO Auto-generated method stub
		return mTheme;
	}

	private View xmlPullParser2view(XmlResourceParser xmlPullParser) {
		View view = LayoutInflater.from(mContext).inflate(xmlPullParser, null);
		xmlPullParser.close();
		return view;
	}

	private Animation xmlPullParser2animation(XmlResourceParser xmlPullParser) {
		Class c = AnimationUtils.class;
		Class parameterTypes[] = { Context.class, XmlPullParser.class };
		try {
			Method method = c.getDeclaredMethod("createAnimationFromXml",
					parameterTypes);
			method.setAccessible(true);
			Object args[] = { mContext, xmlPullParser };
			Animation animation = (Animation) method.invoke(null, args);
			xmlPullParser.close();
			return animation;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Animation animValue(String name) {
		// TODO Auto-generated method stub
		return xmlPullParser2animation(mResources.getAnimation(anim(name)));
	}

	@Override
	public Animation animValue(int animId) {
		// TODO Auto-generated method stub
		return xmlPullParser2animation(mResources.getAnimation(animId));
	}
}
