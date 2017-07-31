/*
 * Copyright (c) 2017. Visionet and/or its affiliates. All right reserved.
 * VISIONET PROPRIETARY/CONFIDENTIAL.
 */
package com.example.visionet.makechoice.pagerAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TC.Ubuntu
 * @since 2017/7/23.
 */
public class ContentAdapter extends PagerAdapter {

	private List<View> pageViews = new ArrayList<>();

	public ContentAdapter(List<View> pageViews) {
		this.pageViews = pageViews;
	}

	@Override
	public int getCount() {
		return pageViews.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		View positionView = pageViews.get(position);
		container.addView(positionView);
		return positionView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(pageViews.get(position));
	}
}
