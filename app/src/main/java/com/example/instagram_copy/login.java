package com.example.instagram_copy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {


    private EditText login_email, login_pass;
    private ImageView btn_sign_up;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_pass = findViewById(R.id.login_pass);

        btn_login = findViewById(R.id.btn_login);

        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (new Intent(login.this, Signup.class));
                startActivity(intent);    }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail = login_email.getText().toString();
                String mPass = login_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            login_email.setText("");
                            login_pass.setText("");

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                String mEmail = jsonObject.getString("mEmail");
                                String mPass = jsonObject.getString("mPass");

                                Toast.makeText(getApplicationContext(), "로그인에 성공하셨습니다!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this, Result.class);
                                intent.putExtra("mEmail", mEmail);
                                intent.putExtra("mPass", mPass);
                                startActivity(intent);
                            } else { //로그인 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인 실패.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                LoginRequest loginRequest = new LoginRequest( mEmail,mPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(login.this);
                queue.add(loginRequest);
            }


        });}}
