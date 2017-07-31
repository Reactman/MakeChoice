package com.example.visionet.makechoice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.example.visionet.makechoice.activity.OptionAddActivity;
import com.example.visionet.makechoice.common.MenuIconSetable;
import com.example.visionet.makechoice.entity.ChoiceOption;
import com.example.visionet.makechoice.listViewAdapter.main.ChoiceOptionAdapter;
import com.example.visionet.makechoice.pagerAdapter.ContentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private List<TextView> bottomToolbarTextViews = new ArrayList<>();
    private List<ImageView> bottomToolbarIconViews = new ArrayList<>();

    private View choiceShakeView;
    private View choiceEditView;
    private View choiceHelpView;
    private View authorView;
    private ViewPager viewPager;
    private List<View> positionViewContainer = new ArrayList<>();

    private SensorManager sensorManager;
    private Vibrator vibrator;

    private List<ChoiceOption> choiceOptionList;
    private Boolean liewInit = false;

    private int currentViewPagerPos = 0;
    private static int DEFAULT_BOTTOMTOOLBAR_CONTENTCOLOR;
    private static int SELECTED_BOTTOMTOOLBAR_CONTENTCOLOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DEFAULT_BOTTOMTOOLBAR_CONTENTCOLOR = getResources().getColor(R.color.default_ivory);
        SELECTED_BOTTOMTOOLBAR_CONTENTCOLOR = getResources().getColor(R.color.forestGreen);
        initView();
    }

    @Override
    protected void onResume() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 取消监听器
        if (sensorManager != null) {
            sensorManager.unregisterListener(sensorEventListener);
        }
        super.onPause();
    }

    private void getData() {
        choiceOptionList = new ArrayList<>();
        for(int i=0; i<30; i++) {
            ChoiceOption option = new ChoiceOption();
            option.setOptionName("选项"+String.valueOf(i));
            choiceOptionList.add(option);
        }
    }

    private void listViewTest() {
        if(!liewInit) {
            getData();
            ListView choiceOptionView = (ListView) findViewById(R.id.choiceList);
            ChoiceOptionAdapter choiceOptionAdapter = new ChoiceOptionAdapter(this,
                    R.layout.main_list_choice_option, choiceOptionList);          //关联数据和子布局
            choiceOptionView.setAdapter(choiceOptionAdapter);                   //绑定数据和适配器
            choiceOptionView.getAdapter();

            choiceOptionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //点击每一行的点击事件

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position,
                        long id) {
//                    ChoiceOption choiceOption = choiceOptionList.get(position);         //获取点击的那一行
//                    Toast.makeText(HomeActivity.this, choiceOption.getOptionName(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HomeActivity.this, OptionAddActivity.class);
        /* 启动一个新的Activity */
                    HomeActivity.this.startActivity(intent);
                }
            });
            liewInit = true;
        }
    }

    private void initView() {
        LinearLayout shakeOption = (LinearLayout) findViewById(R.id.bottomBar_shake);
        shakeOption.setOnClickListener(this);
        LinearLayout editOption = (LinearLayout) findViewById(R.id.bottomBar_edit);
        editOption.setOnClickListener(this);
        LinearLayout helpOption = (LinearLayout) findViewById(R.id.bottomBar_help);
        helpOption.setOnClickListener(this);
        LinearLayout authorOption = (LinearLayout) findViewById(R.id.bottomBar_author);
        authorOption.setOnClickListener(this);

        //viewPager init
        this.viewPager = (ViewPager) findViewById(R.id.home_viewPager);
        choiceShakeView = View.inflate(HomeActivity.this, R.layout.main_page_choice_shake, null);
        choiceEditView = View.inflate(HomeActivity.this, R.layout.main_page_choice_edit, null);
        choiceHelpView = View.inflate(HomeActivity.this, R.layout.main_page_choice_help, null);
        authorView = View.inflate(HomeActivity.this, R.layout.main_page_author, null);
        positionViewContainer.add(choiceShakeView);
        positionViewContainer.add(choiceEditView);
        positionViewContainer.add(choiceHelpView);
        positionViewContainer.add(authorView);
        ContentAdapter contentAdapter = new ContentAdapter(positionViewContainer);
        viewPager.setAdapter(contentAdapter);
        viewPager.setCurrentItem(currentViewPagerPos);
        viewPager.setOnPageChangeListener(this);

        //bottom toolbar view init
        TextView shakeChoiceTextView = (TextView) findViewById(R.id.text_shake_choice);
        ImageView shakeChoiceIconView = (ImageView) findViewById(R.id.icon_shake_choice);
        bottomToolbarTextViews.add(shakeChoiceTextView);
        bottomToolbarIconViews.add(shakeChoiceIconView);
        TextView editChoiceTextView = (TextView) findViewById(R.id.text_edit);
        ImageView editChoiceIconView = (ImageView) findViewById(R.id.icon_edit);
        bottomToolbarTextViews.add(editChoiceTextView);
        bottomToolbarIconViews.add(editChoiceIconView);
        TextView helpTextView = (TextView) findViewById(R.id.text_help);
        ImageView helpIconView = (ImageView) findViewById(R.id.icon_help);
        bottomToolbarTextViews.add(helpTextView);
        bottomToolbarIconViews.add(helpIconView);
        TextView authorTextView = (TextView) findViewById(R.id.text_author);
        ImageView authorIconView = (ImageView) findViewById(R.id.icon_author);
        bottomToolbarTextViews.add(authorTextView);
        bottomToolbarIconViews.add(authorIconView);
        resetBottomToolbar(currentViewPagerPos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_dropdown_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        MenuIconSetable.setIconEnable(menu);
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == positionViewContainer.indexOf(choiceShakeView)) {
            sensorManager.registerListener(
                    sensorEventListener,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else if(position == positionViewContainer.indexOf(choiceEditView)) {
            listViewTest();
        } else {
            if (sensorManager != null) {// 取消监听器
                sensorManager.unregisterListener(sensorEventListener);
            }
        }
        this.resetBottomToolbar(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 19;
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TextView textView = (TextView) findViewById(R.id.shake);
            textView.setText("HELLO WORLD");
        }

    };

    private void resetBottomToolbar(int selectedPagePos) {
        bottomToolbarIconViews.get(currentViewPagerPos).setColorFilter(DEFAULT_BOTTOMTOOLBAR_CONTENTCOLOR);
        bottomToolbarTextViews.get(currentViewPagerPos).setTextColor(DEFAULT_BOTTOMTOOLBAR_CONTENTCOLOR);
        bottomToolbarIconViews.get(selectedPagePos).setColorFilter(SELECTED_BOTTOMTOOLBAR_CONTENTCOLOR);
        bottomToolbarTextViews.get(selectedPagePos).setTextColor(SELECTED_BOTTOMTOOLBAR_CONTENTCOLOR);
        currentViewPagerPos = selectedPagePos;
    }

    @Override
    public void onClick(View view) {
        int selectedPos = currentViewPagerPos;
        switch (view.getId()) {
            case R.id.bottomBar_shake:
                selectedPos = 0;
                break;
            case R.id.bottomBar_edit:
                selectedPos = 1;
                break;
            case R.id.bottomBar_help:
                selectedPos = 2;
                break;
            case R.id.bottomBar_author:
                selectedPos = 3;
                break;
            case R.id.option_add:
                redictToAddOption(HomeActivity.this, OptionAddActivity.class, null);
            default:
                break;
        }
        if(currentViewPagerPos != selectedPos) {
            resetBottomToolbar(selectedPos);
            viewPager.setCurrentItem(selectedPos);
        }
    }

    private void redictToAddOption(Context currentContext, Class targetClass,
            Map<String, String> transferData) {
        Intent intent = new Intent(currentContext, targetClass);
        if(transferData != null) {
            for(String dataKey : transferData.keySet()) {
                intent.putExtra(dataKey, transferData.get(dataKey));
            }
        }
        /* 启动一个新的Activity */
        currentContext.startActivity(intent);
    }
}
