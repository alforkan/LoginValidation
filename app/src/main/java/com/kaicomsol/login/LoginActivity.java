package com.kaicomsol.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout input_layout_phone,input_layout_password;
    private TextInputEditText edt_phone,edt_password;
    private TextView txt_signup;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_layout_phone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        input_layout_password = (TextInputLayout) findViewById(R.id.input_layout_password);
        edt_phone = (TextInputEditText) findViewById(R.id.edt_phone);
        edt_password = (TextInputEditText) findViewById(R.id.edt_password);
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        button = (Button) findViewById(R.id.button);

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLogin();
            }
        });
    }

    private void submitLogin(){
        if (!isPhoneValid())
            return;

        if (!isPasswordValid())
            return;

        Toast.makeText(LoginActivity.this,"Login success",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isPhoneValid(){
        String phone = edt_phone.getText().toString();
        if (TextUtils.isEmpty(phone)){
            input_layout_phone.setError("Please enter your phone");
            return false;
        }else if (!isPhone(phone)){
            input_layout_phone.setError("Please enter valid phone number");
            return false;
        }else {
            input_layout_phone.setError(null);
            return true;
        }
    }

    private boolean isPasswordValid(){
        String password = edt_password.getText().toString();
        if (TextUtils.isEmpty(password)){
            input_layout_password.setError("Please enter your password");
            return false;
        }else if (password.length() < 6){
            input_layout_password.setError("Password length too short");
            return false;
        }else {
            input_layout_password.setError(null);
            return true;
        }
    }

    private boolean isPhone(String phone){
        return Patterns.PHONE.matcher(phone).matches();
    }
}
