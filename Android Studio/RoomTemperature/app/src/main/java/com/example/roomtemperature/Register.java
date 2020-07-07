package com.example.roomtemperature;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText useremail;
    private EditText userphone;
    private EditText userpassword;
    private EditText userrepassword;
    private Button register_btn;
    private TextView regerror;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    RegData regData;
    Intent intent;
    String name,email,phoneno,password,repassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        regData = new RegData();
        ref = FirebaseDatabase.getInstance().getReference().child("Users");


        username = (EditText)findViewById(R.id.reg_name);
        useremail = (EditText)findViewById(R.id.reg_email);
        userphone = (EditText)findViewById(R.id.reg_phone);
        userpassword = (EditText)findViewById(R.id.reg_password);
        userrepassword = (EditText)findViewById(R.id.reg_repassword);
        register_btn = (Button)findViewById(R.id.register_btn);
        regerror = (TextView)findViewById(R.id.reg_errormsg);
        progressBar = (ProgressBar)findViewById(R.id.reg_progress);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regNewUser();
           }
        });
    }

    public void regNewUser(){

        name = username.getText().toString().trim();
        email = useremail.getText().toString().trim();
        phoneno = userphone.getText().toString().trim();
        password = userpassword.getText().toString().trim();
        repassword = userrepassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            regerror.setText("Enter user name!");
            return;
        }if (TextUtils.isEmpty(phoneno)) {
            regerror.setText("Enter phone number!");
            return;
        }
        if(phoneno.length() < 10){
            regerror.setText("Enter a valid Phone number");
            return;
        }
        if (TextUtils.isEmpty(email)){
            regerror.setText("Enter email address!");
            return;
        }
        if (TextUtils.isEmpty(password)){
            regerror.setText("Enter password!");
            return;
        }
        if (password.length() < 6){
            regerror.setText("Password must contain at least 6 characters!");
            return;
        }
        if (TextUtils.isEmpty(repassword)) {
            regerror.setText("Enter re-password!");
            return;
        }
        if ((password.equals(repassword))== false){
            regerror.setText("Passwords not match!");
            return;
        }

        //   progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String id = mAuth.getCurrentUser().getUid();
                            Toast.makeText(Register.this,id,Toast.LENGTH_SHORT).show();

                            regData.setUserName(name);
                            regData.setUserEmail(email);
                            regData.setUserPhone(phoneno);
                            regData.setUserPassword(password);
                            ref.child(id).setValue(regData);

                            intent = new Intent(Register.this,MainActivity.class);
                            startActivity(intent);
/*
                            intent = new Intent(Register.this, VerifyRegister.class);
                            intent.putExtra("name",name);
                            intent.putExtra("email",email);
                            intent.putExtra("phoneno",phoneno);
                            intent.putExtra("password",password);
                            startActivity(intent);
*/
                            finish();
                        }
                    }
                });


/*        intent = new Intent(Register.this, VerifyRegister.class);
        intent.putExtra("name",name);
        intent.putExtra("email",email);
        intent.putExtra("phoneno",phoneno);
        intent.putExtra("password",password);
        startActivity(intent);

        finish();
*/
    }

}
