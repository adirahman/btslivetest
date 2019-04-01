package com.bts.btsapimodule;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BTSClient {

    public static String BASE_URL = "http://3.0.56.177:4000/api/";

    public static BTSClient instace;
    private static Retrofit retrofit;

    private static BTSService service;

    private BTSClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        Log.d("URL",BASE_URL);
    }

    public static BTSClient getInstance(){
        if(instace == null){
            instace = new BTSClient();
        }
        return instace;
    }

    public static BTSService getService(){
        service = retrofit.create(BTSService.class);
        return service;
    }
}

