package com.example.instagram_copy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Signup extends AppCompatActivity {

    private EditText et_phone, et_email, et_pass;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);


        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mPhone = et_phone.getText().toString();
                String mEmail = et_email.getText().toString();
                String mPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(),"회원가입이 되었습니다!", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(Signup.this,login.class);
//                                startActivity(intent);
                                finish(); // 이미 기존 로그인 액티비티가 떠있기 때문에 현재 화면만 종료 하면 로그인화면으로 돌아가진다..
                            } else {
                                Toast.makeText(getApplicationContext(),"회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                //서버로 Volley이용 요청함
                RegisterRequest registerRequest = new RegisterRequest(mPhone,mEmail,mPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Signup.this);
                queue.add(registerRequest);

            }
        });

    }
}
