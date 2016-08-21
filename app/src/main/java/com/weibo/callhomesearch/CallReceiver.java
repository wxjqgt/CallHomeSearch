package com.weibo.callhomesearch;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.weibo.callhomesearch.bean.GetZoneResult;
import com.weibo.callhomesearch.util.Constant;
import com.weibo.callhomesearch.util.DLog;
import com.weibo.callhomesearch.util.MainApp;
import com.weibo.callhomesearch.util.SPUtil;
import com.weibo.callhomesearch.util.VolleyUtil;

import java.util.HashMap;
import java.util.Map;

public class CallReceiver extends BroadcastReceiver {

    private Context context;
    private String num;
    private TextView txNumber, txCatName;
    private static final String ISFIRST = "first";
    private LinearLayout mFloatLayout;//定义悬浮窗口布局
    private WindowManager.LayoutParams wmParams;
    private WindowManager mWindowManager;//创建悬浮窗口设置布局参数的对象

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        //拨打电话
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            num = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            telRinging(num);
        } else {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    private PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            telRinging(incomingNumber);
            switch (state) {
               /* //电话等待接听
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
                //电话接听
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;*/
                //电话挂机
                case TelephonyManager.CALL_STATE_IDLE:
                    popPhoneRemove();
                    break;
            }
        }
    };

    /**
     * 打开来电悬浮窗界面
     *
     * @param number
     */
    public void telRinging(final String number) {
        if (!SPUtil.getBoolean(ISFIRST)) {
            //第一次启动悬浮窗，延迟两秒后显示界面,并且把firstNew设置为true
            SPUtil.putBoolean(ISFIRST, true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    popPhone(number);
                }
            }, 1000);
        } else {
            //如果悬浮窗已经显示
        }
    }

    private void initView(String phone){
        LayoutInflater inflater = LayoutInflater.from(context);
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.callhomelayout, null, false);//获取浮动窗口视图所在布局
        txNumber = (TextView) mFloatLayout.findViewById(R.id.txNumber);
        txCatName = (TextView) mFloatLayout.findViewById(R.id.txCatName);
        txNumber.setText(phone);

        VolleyUtil.GETRequestString(Constant.url + phone, new VolleyUtil.OnStringRequest() {
            @Override
            public void OnSuccess(String successResult) {
                String result = successResult.replace("__GetZoneResult_ = ", "");
                GetZoneResult getZoneResult = JSON.parseObject(result, GetZoneResult.class);
                if (!TextUtils.isEmpty(getZoneResult.getCarrier())) {
                    txCatName.setText(getZoneResult.getCarrier());
                } else if (!TextUtils.isEmpty(getZoneResult.getProvince())){
                    txCatName.setText(getZoneResult.getProvince());
                }
            }

            @Override
            public void OnError(VolleyError errorResult) {
                txCatName.setText("太高深查不到！");
            }
        });

        txCatName.setTextColor(Color.BLACK);
        txNumber.setTextColor(Color.BLACK);
    }
    /**
     * 悬浮窗的具体实现
     */
    private void popPhone(String phone) {
        initView(phone);
        wmParams = new WindowManager.LayoutParams();

        //定义WindowManager.LayoutParams类型,TYPE_SYSTEM_ERROR为系统内部错误提示，显示于所有内容之上
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;

        //系统服务，注意这里必须加getApplicationContext(),否则无法把悬浮窗显示在最上层
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; //不能抢占聚焦点
        wmParams.flags = wmParams.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        wmParams.flags = wmParams.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; //排版不受限制
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = getWindowHeight(context) / 5;//屏幕的一半高度
        wmParams.gravity = Gravity.TOP;//居上显示
        wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

        mWindowManager.addView(mFloatLayout, wmParams); //创建View
    }


    /**
     * 移除悬浮窗
     */
    public void popPhoneRemove() {
        if (mFloatLayout != null) {
            mWindowManager.removeView(mFloatLayout);
        }
        mFloatLayout = null;//必须加入此语句，否则会windowManager会找不到view
        SPUtil.putBoolean(ISFIRST, false);
        MainApp.closeSp();
        System.exit(0);
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    private int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);//系统服务
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;   // 屏幕高度（像素）
    }


}
