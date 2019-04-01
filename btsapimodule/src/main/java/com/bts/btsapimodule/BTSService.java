package com.bts.btsapimodule;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BTSService {

    @POST("users/signin")
    Call<SignInResponse> getSignIn(@HeaderMap Map<String,String> header, @Body String request);

    @POST("users/signup")
    Call<SignInResponse> getSignUp(@HeaderMap Map<String,String> header, @Body SignUpRequest request);

    @GET("users/")
    Call<UserModelResponse> getUsers(@HeaderMap Map<String,String> header,@Body String request);

    @POST("shopping")
    Call<NewShoppingResponse> createNewShopping(@HeaderMap Map<String,String> header,@Body ShoppingRequest request);

    @GET("shopping")
    Call<BaseResponse> getAllShoppings(@HeaderMap Map<String,String> header,@Body String request);

    @GET("shopping/{id}")
    Call<BaseResponse> getShoppingById(@HeaderMap Map<String,String> header,@Path("id") int shopId);

    @PUT("shopping/{id}")
    Call<BaseResponse> updateShoppingById(@Path("id") int shopId,@Body String request);

    @DELETE("shopping/{id}")
    Call<BaseResponse> deleteShoppingById(@HeaderMap Map<String,String> header,@Path("id") int shopId);
}
