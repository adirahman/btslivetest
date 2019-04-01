package com.bts.btstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bts.btsapimodule.BaseResponse;
import com.bts.btsapimodule.RepoBTS;
import com.bts.btsapimodule.ResponseCourier;
import com.bts.btsapimodule.SignInResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity implements ResponseCourier {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
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
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);

        repo = new RepoBTS(LoginActivity.this,this,"");

        etEmail.setText("rahmanadi9@gmail.com");
        etPassword.setText("123456");
    }

    @OnClick({R.id.btnLogin, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if(isValid()){
                    repo.getLogin(etEmail.getText().toString(),etPassword.getText().toString());
                }
                break;
            case R.id.btnRegister:
                RegisterActivity.startThisActivity(LoginActivity.this);
                break;
        }
    }

    @Override
    public void getDataResponse(BaseResponse data, String message) {
        if(data != null && data instanceof SignInResponse){
            SignInResponse response = (SignInResponse) data;
            PrefHelper.saveToPref(LoginActivity.this,"token",response.token);
            MainActivity.startThisActivity(LoginActivity.this);
        }else{
            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(){
        boolean isValid = true;

        if(TextUtils.isEmpty(etEmail.getText())){
            isValid = false;
            etEmail.setError("please input");
        }

        if(TextUtils.isEmpty(etPassword.getText())){
            isValid = false;
            etPassword.setError("please input");
        }

        return isValid;
    }
}
