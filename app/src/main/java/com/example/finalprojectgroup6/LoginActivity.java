package com.example.finalprojectgroup6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    DBHelper dbh;

    Intent loginToSignupIntent;
    Button registerRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.edtEmailLogin);
        password = (EditText) findViewById(R.id.edtPasswordLogin);
        login = (Button) findViewById(R.id.btnLogin);
        registerRedirect = (Button) findViewById(R.id.btnRegisterRedirect);
        dbh = new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    Cursor cursor = dbh.findUser(email.getText().toString().trim());

                    if(cursor.getCount() == 1){
                        User user = new User();
                        user.setUserId(cursor.getInt(0));
                        user.setName(cursor.getString(1));
                        user.setEmail(cursor.getString(2));
                        user.setPassword(cursor.getString(3));

                        if(password.getText().toString().trim().equals(user.getPassword())){
                            Toast.makeText(v.getContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(v.getContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(v.getContext(),"User does not exist",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        registerRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginToSignupIntent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(loginToSignupIntent);
                finish();
            }
        });

    }

    private boolean validate() {
        if(!email.getText().toString().trim().equals("")){
            if(!password.getText().toString().trim().equals("")){
                return  true;
            }else {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                return  false;
            }

        }
        else{
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return  false;
        }

    }

}