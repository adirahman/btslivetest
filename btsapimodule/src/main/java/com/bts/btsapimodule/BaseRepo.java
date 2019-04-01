package com.bts.btsapimodule;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseRepo {
    public Context context;
    public ResponseCourier courier;
    public Map<String,String> headerRequest = new HashMap<>();
    public String token;

    public BaseRepo(Context context,ResponseCourier courier,String token){
        this.context = context;
        this.courier = courier;
        this.token = token;

        headerRequest.put("Content-Type","application/json");
        if(!TextUtils.isEmpty(token)){
            headerRequest.put("Authorization","Bearer "+token);
        }
        //headerRequest.put("Authorization","Bearer "+token);
    }
}
