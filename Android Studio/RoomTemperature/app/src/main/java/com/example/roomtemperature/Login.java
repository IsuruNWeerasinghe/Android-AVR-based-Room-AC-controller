package com.example.roomtemperature;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private Button signin_btn;
    private Button signup_btn;
    private Button fogotpw_btn;
    private EditText useremail_txt;
    private EditText userpw_txt;
    private TextView errormsg_txt;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        useremail_txt = (EditText)findViewById(R.id.login_email);
        userpw_txt = (EditText)findViewById(R.id.login_password);
        errormsg_txt = (TextView)findViewById(R.id.login_errormsg);
        signup_btn = (Button)findViewById(R.id.login_reg_btn);
        signin_btn = (Button) findViewById(R.id.login_btn);
        fogotpw_btn = (Button)findViewById(R.id.login_fogotpw_btn);
        progressBar = (ProgressBar)findViewById(R.id.login_progress);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
            if (!auth.getCurrentUser().getPhoneNumber().isEmpty()){

            }
            //finish();
        }


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
        fogotpw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fogotpwintent = new Intent(Login.this,FogotPassword.class);
                startActivity(fogotpwintent);
            }
        });
    }

    private void login(){

        String email = useremail_txt.getText().toString().trim();
        final String password = userpw_txt.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            errormsg_txt.setText("Enter email address!");
            return;
        }
        if (TextUtils.isEmpty(password)){
            errormsg_txt.setText("Enter password!");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.VISIBLE);
        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                userpw_txt.setError("password too short");
                            } else {
                                Toast.makeText(Login.this,"Auth field", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

    }

}
