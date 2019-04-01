package com.bts.btsapimodule;

import android.content.Context;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoBTS extends BaseRepo{

    public RepoBTS(Context context, ResponseCourier courier, String token) {
        super(context, courier, token);
    }

    public void getRegister(SignUpRequest userRegister){
        /*JsonObject userRequest = new JsonObject();
        userRequest.addProperty("username",userRegister.username);
        userRequest.addProperty("email",userRegister.email);
        userRequest.addProperty("encrypted_password",userRegister.encryted_password);
        userRequest.addProperty("phone",userRegister.phone);
        userRequest.addProperty("address",userRegister.address);
        userRequest.addProperty("city",userRegister.city);
        userRequest.addProperty("country",userRegister.country);
        userRequest.addProperty("name",userRegister.name);
        userRequest.addProperty("postcode",userRegister.postcode);


        JsonObject request = new JsonObject();
        request.addProperty("user",userRequest.toString());*/

        try{
            Call<SignInResponse> call = BTSClient.getInstance().getService().getSignUp(headerRequest,userRegister);
            call.enqueue(new Callback<SignInResponse>() {
                @Override
                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                    if(response.body() != null){
                        courier.getDataResponse(response.body(),"sukses");
                    }else{
                        courier.getDataResponse(null,"failed");
                    }
                }

                @Override
                public void onFailure(Call<SignInResponse> call, Throwable t) {
                    courier.getDataResponse(null,t.getMessage());
                }
            });
        }catch (Exception e){
            courier.getDataResponse(null,e.getMessage());
        }
    }

    public void getLogin(String email,String password){
        JsonObject request = new JsonObject();
        request.addProperty("email",email);
        request.addProperty("password",password);

        try{
            Call<SignInResponse> call = BTSClient.getInstance().getService().getSignIn(headerRequest,request.toString());
            call.enqueue(new Callback<SignInResponse>() {
                @Override
                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                    if(response.body() != null){
                        courier.getDataResponse(response.body(),"sukses");
                    }else{
                        courier.getDataResponse(null,"failed");
                    }
                }

                @Override
                public void onFailure(Call<SignInResponse> call, Throwable t) {
                    courier.getDataResponse(null,t.getMessage());
                }
            });
        }catch (Exception e){
            courier.getDataResponse(null,e.getMessage());
        }
    }

    public void createNewShopping(ShoppingRequest newShopping){
        try{
            Call<NewShoppingResponse> call = BTSClient.getInstance().getService().createNewShopping(headerRequest,newShopping);
            call.enqueue(new Callback<NewShoppingResponse>() {
                @Override
                public void onResponse(Call<NewShoppingResponse> call, Response<NewShoppingResponse> response) {
                    if(response.body() != null){
                        courier.getDataResponse(response.body(),"sukses");
                    }else{
                        courier.getDataResponse(null,"failed");
                    }
                }

                @Override
                public void onFailure(Call<NewShoppingResponse> call, Throwable t) {
                    courier.getDataResponse(null,t.getMessage());
                }
            });
        }catch (Exception e){
            courier.getDataResponse(null,e.getMessage());
        }
    }

    public void getAllUser(){
        JsonObject request = new JsonObject();
        try{
           Call<UserModelResponse> call = BTSClient.getInstance().getService().getUsers(headerRequest,request.toString());
           call.enqueue(new Callback<UserModelResponse>() {
               @Override
               public void onResponse(Call<UserModelResponse> call, Response<UserModelResponse> response) {
                   if(response.body() != null){
                       courier.getDataResponse(response.body(),"sukses");
                   }else{
                       courier.getDataResponse(null,"failed");
                   }
               }

               @Override
               public void onFailure(Call<UserModelResponse> call, Throwable t) {
                   courier.getDataResponse(null,t.getMessage());
               }
           });
        }catch (Exception e){
            courier.getDataResponse(null,e.getMessage());
        }
    }
}
