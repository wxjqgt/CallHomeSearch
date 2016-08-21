package com.weibo.callhomesearch.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by Ken on 2015/11/16.
 */
public class VolleyUtil {

    public static RequestQueue requestQueue;

    public static void POSTRequestString(String url, final Map<String, String> params, final OnStringRequest onStringRequest){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onStringRequest.OnSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onStringRequest.OnError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestQueue.add(request);
    }

    public static void GETRequestString(String url,final OnStringRequest onStringRequest){
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onStringRequest.OnSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onStringRequest.OnError(error);
            }
        });
        requestQueue.add(request);
    }


    public static void initVolley(Context context){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
    }


    public interface OnStringRequest{
        void OnSuccess(String successResult);
        void OnError(VolleyError errorResult);
    }

}
