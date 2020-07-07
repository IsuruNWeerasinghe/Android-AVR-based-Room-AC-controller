package com.example.roomtemperature;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FogotPassword extends AppCompatActivity {

    private EditText emailtxt;
    private Button resetbtn;
    private TextView errormsg;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_password);

        emailtxt = (EditText)findViewById(R.id.fogotpw_email);
        resetbtn = (Button)findViewById(R.id.fogotpw_rst_btn);
        errormsg = (TextView)findViewById(R.id.fogotpw_errormsg);
        auth = FirebaseAuth.getInstance();

        resetbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = emailtxt.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    errormsg.setText("Enter your registered email id");
                    return;
                }
                //progressBar1.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(FogotPassword.this, "We have sent you instructions " +
                                            "to reset your password!", Toast.LENGTH_LONG).show();
                                    Intent intent =new Intent(FogotPassword.this,Login.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(FogotPassword.this, "Failed to send reset email!", Toast.LENGTH_LONG).show();
                                }
                                //progressBar1.setVisibility(View.GONE);

                            }
                        });
            }
        });
    }
}
