/*
 * Copyright (c) 2017. Visionet and/or its affiliates. All right reserved.
 * VISIONET PROPRIETARY/CONFIDENTIAL.
 */
package com.example.visionet.makechoice.common;

import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;

import java.lang.reflect.Method;

/**
 * @author TC.Ubuntu
 * @since 2017/7/27.
 */
public class MenuIconSetable {

	public static void setIconEnable(Menu menu) {
		if (menu != null) {
			if (menu.getClass() == MenuBuilder.class) {
				try {
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true); m.invoke(menu, true);
				} catch (Exception e) {

				}
			}
		}
	}
}
