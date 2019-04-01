package com.bts.btsapimodule;

public class NewShoppingResponse extends BaseResponse<NewShoppingResponse.Shopping>{

    public Shopping data;


    public class Shopping{
        public String createddate;
        public int id;
        public String name;
    }
}
