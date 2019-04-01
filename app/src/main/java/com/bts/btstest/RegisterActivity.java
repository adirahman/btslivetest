package com.bts.btstest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bts.btsapimodule.BaseResponse;
import com.bts.btsapimodule.RepoBTS;
import com.bts.btsapimodule.ResponseCourier;
import com.bts.btsapimodule.SignInResponse;
import com.bts.btsapimodule.SignUpModel;
import com.bts.btsapimodule.SignUpRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity implements ResponseCourier {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.etCountry)
    EditText etCountry;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPostcode)
    EditText etPostcode;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    Unbinder unbinder;

    RepoBTS repo;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);

        repo = new RepoBTS(RegisterActivity.this,this,"");
    }

    public static void startThisActivity(Context context) {
        Intent i = new Intent(context, RegisterActivity.class);
        context.startActivity(i);
    }

    @Override
    public void getDataResponse(BaseResponse data, String message) {
        if(data != null && data instanceof SignInResponse){
            Toast.makeText(RegisterActivity.this,"succes",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnRegister)
    public void onViewClicked() {

        if(isValid()){
            SignUpModel signUp = new SignUpModel(etUsername.getText().toString(),
                    etEmail.getText().toString(),
                    etPassword.getText().toString(),
                    etPhone.getText().toString(),
                    etAddress.getText().toString(),
                    etCity.getText().toString(),
                    etCountry.getText().toString(),
                    etName.getText().toString(),
                    etPostcode.getText().toString());
            SignUpRequest registerRequest = new SignUpRequest(signUp);
            repo.getRegister(registerRequest);
        }
    }

    private boolean isValid(){
        boolean isValid = true;

        if(TextUtils.isEmpty(etUsername.getText())){
            isValid = false;
            etUsername.setError("please input");
        }

        if(TextUtils.isEmpty(etEmail.getText())){
            isValid = false;
            etEmail.setError("please input");
        }

        if(TextUtils.isEmpty(etPassword.getText())){
            isValid = false;
            etPassword.setError("please input");
        }

        if(TextUtils.isEmpty(etPhone.getText())){
            isValid = false;
            etPhone.setError("please input");
        }

        if(TextUtils.isEmpty(etAddress.getText())){
            isValid = false;
            etAddress.setError("please input");
        }

        if(TextUtils.isEmpty(etCity.getText())){
            isValid = false;
            etCity.setError("please input");
        }

        if(TextUtils.isEmpty(etCountry.getText())){
            isValid = false;
            etCountry.setError("please input");
        }

        if(TextUtils.isEmpty(etName.getText())){
            isValid = false;
            etName.setError("please input");
        }

        if(TextUtils.isEmpty(etPostcode.getText())){
            isValid = false;
            etPostcode.setError("please input");
        }

        return isValid;
    }
}
