package com.weibo.callhomesearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.weibo.callhomesearch.bean.GetZoneResult;
import com.weibo.callhomesearch.util.Constant;
import com.weibo.callhomesearch.util.DLog;
import com.weibo.callhomesearch.util.VolleyUtil;

import java.util.HashMap;
import java.util.Map;

import static com.weibo.callhomesearch.R.id.txCatName;
import static com.weibo.callhomesearch.util.Constant.url;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
