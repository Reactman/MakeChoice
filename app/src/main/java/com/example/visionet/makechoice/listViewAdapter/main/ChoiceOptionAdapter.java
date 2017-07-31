/*
 * Copyright (c) 2017. Visionet and/or its affiliates. All right reserved.
 * VISIONET PROPRIETARY/CONFIDENTIAL.
 */
package com.example.visionet.makechoice.listViewAdapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.visionet.makechoice.R;
import com.example.visionet.makechoice.entity.ChoiceOption;

import java.util.List;

/**
 * @author TC.Ubuntu
 * @since 2017/7/29.
 */
public class ChoiceOptionAdapter extends ArrayAdapter<ChoiceOption> {

	private int resourceId;

	public ChoiceOptionAdapter(Context context, int resource, List<ChoiceOption> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}



	@Override
	//getView方法在每个子项被滚动到屏幕内的时候都会被调用，每次都将布局重新加载一边
	//第一个参数表示位置，第二个参数表示缓存布局，第三个表示绑定的view对象
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		//实例ViewHolder，当程序第一次运行，保存获取到的控件，提高效率
		ViewHolder viewHolder;
		//convertView为空代表布局没有被加载过，即getView方法没有被调用过，需要创建
		if(convertView == null){
			viewHolder = new ViewHolder();
			// 得到子布局，非固定的，和子布局id有关
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder.tvName = view.findViewById(R.id.tvName);   //获取控件
			view.setTag(viewHolder);
		}else{
			view=convertView;           //convertView不为空代表布局被加载过，只需要将convertView的值取出即可
			viewHolder=(ViewHolder) view.getTag();
		}
		ChoiceOption choiceOption = getItem(position);
		viewHolder.tvName.setText(choiceOption.getOptionName());
		return view;

	}
}

class ViewHolder{      //当布局加载过后，保存获取到的控件信息。
	TextView tvName;
}
