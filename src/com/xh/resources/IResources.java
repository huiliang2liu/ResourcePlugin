package com.xh.resources;

import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;

/**
 * Resources com.xh.resources 2018 2018-3-7 下午12:19:41 instructions：
 * author:liuhuiliang email:825378291@qq.com
 **/

public interface IResources {
	/**
	 * 
	 * 2018 2018-3-7 下午12:21:10 annotation： author：liuhuiliang email
	 * ：825378291@qq.com
	 * 
	 * @return AssetManager
	 */
	public AssetManager getAssetManager();

	/**
	 * 
	 * 2018 2018-3-7 下午12:21:30 annotation： author：liuhuiliang email
	 * ：825378291@qq.com
	 * 
	 * @return Resources
	 */
	public Resources getResources();

	public String packageName();

	public Theme getTheme();

	public int name2id(String name, String type);

	public boolean equals(String packageName);

	public int style(String name);

	public int dimen(String name);

	public int color(String name);

	public int colorValue(String name);

	public ColorStateList colorList(String name);

	public int colorValue(int colorId);

	public ColorStateList colorList(int colorId);

	public int anim(String name);

	public Animation animValue(String name);

	public Animation animValue(int animId);

	public int raw(String name);

	public int attr(String name);

	public int string(String name);

	public String stringValue(String name);

	public String[] stringValues(String name);

	public String stringValue(int stringId);

	public String[] stringValues(int stringId);

	public int id(String name);

	public int drawable(String name);

	public Drawable drawableValue(String name);

	public Drawable drawableValue(int drawavleId);

	public int layout(String name);

	public View layoutValue(String name);

	public View layoutValue(int layoutId);
}
