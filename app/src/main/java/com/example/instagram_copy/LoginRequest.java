package com.example.instagram_copy;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {


        final static private String URL = "http://forlogin.dothome.co.kr/Login.php";
        private Map<String,String> map;

    public LoginRequest(String mEmail, String mPass, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);

        map = new HashMap<>();
      //  map.put("mPhone", mPhone);
        map.put("mEmail", mEmail);
        map.put("mPass", mPass);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
