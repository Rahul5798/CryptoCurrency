package com.example.finalprojectgroup6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SignupActivity extends AppCompatActivity {
    EditText email;
    EditText name;
    EditText password;
    EditText confirmPassword;
    Button registerButton;
    Button loginRedirect;
    Intent signupToLoginIntent;

    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText) findViewById(R.id.edtEmailRegister);
        name = (EditText) findViewById(R.id.edtNameRegister);
        password = (EditText) findViewById(R.id.edtPasswordRegister);
        confirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        registerButton = (Button) findViewById(R.id.btnRegister);
        loginRedirect = (Button) findViewById(R.id.btnLoginRedirect);
        dbh = new DBHelper(this);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validateData()) {
                   User user = new User();
                   user.setUserId(0);
                   user.setEmail(email.getText().toString().trim());
                   user.setName(name.getText().toString().trim());
                   user.setPassword(password.getText().toString().trim());
                   boolean insertStatus = dbh.insertUser(user);
                   if(insertStatus){
                       Toast.makeText(v.getContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                       startActivity(intent);
                   }else {
                       Toast.makeText(v.getContext(),"Registration failed",Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupToLoginIntent = new Intent(SignupActivity .this,LoginActivity.class);
                startActivity(signupToLoginIntent);
                finish();
            }
        });
    }

    private boolean validateData() {
        String emailStr = email.getText().toString().trim();
        String nameStr = name.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();
        String confirmPasswordStr = confirmPassword.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(!emailStr.equals("")){
            if(emailStr.matches(emailPattern)){
                if(!nameStr.equals("")){
                    if(!passwordStr.equals("")){
                        if(passwordStr.length()<7){
                            Toast.makeText(this,"Password length is too small",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        else {
                            if(!confirmPasswordStr.equals("")){
                                if(confirmPasswordStr.equals(passwordStr)){
                                    return true;
                                }else {
                                    Toast.makeText(this,"Please reenter the same password",Toast.LENGTH_SHORT).show();
                                    return false;
                                }

                            }else {
                                Toast.makeText(this,"Please confirm the password",Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }

                    }else {
                        Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else {
                    Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(this, "Email format is invalid", Toast.LENGTH_SHORT).show();
                return false;
            }

        }else {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}