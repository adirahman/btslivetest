package com.bts.btstest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bts.btsapimodule.BaseResponse;
import com.bts.btsapimodule.NewShoppingResponse;
import com.bts.btsapimodule.RepoBTS;
import com.bts.btsapimodule.ResponseCourier;
import com.bts.btsapimodule.ShoppingModel;
import com.bts.btsapimodule.ShoppingRequest;
import com.bts.btsapimodule.UserModelResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ResponseCourier {

    RepoBTS repoBTS;
    @BindView(R.id.btnCreateNewShop)
    Button btnCreateNewShop;
    @BindView(R.id.btnGetAllUser)
    Button btnGetAllUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        repoBTS = new RepoBTS(MainActivity.this, this, PrefHelper.getPref(MainActivity.this, "token"));

    }

    public static void startThisActivity(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    @Override
    public void getDataResponse(BaseResponse data, String message) {
        if (data != null && data instanceof NewShoppingResponse) {
            NewShoppingResponse a = (NewShoppingResponse) data;
            Toast.makeText(MainActivity.this, a.data.createddate + " ID: " + a.data.id + " name" + a.data.name, Toast.LENGTH_SHORT).show();
        }else if(data != null && data instanceof UserModelResponse){
            UserModelResponse a = (UserModelResponse) data;
            Toast.makeText(MainActivity.this,a.data.size()+" jumlah User",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.btnCreateNewShop, R.id.btnGetAllUser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCreateNewShop:
                ShoppingModel sm = new ShoppingModel("2019-03-18", "new shopping");
                ShoppingRequest sr = new ShoppingRequest(sm);

                repoBTS.createNewShopping(sr);
                break;
            case R.id.btnGetAllUser:
                repoBTS.getAllUser();
                break;
        }
    }
}
