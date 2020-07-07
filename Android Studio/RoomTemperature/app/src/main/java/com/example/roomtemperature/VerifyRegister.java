package com.example.roomtemperature;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyRegister extends AppCompatActivity {

    private EditText verifytxt;
    private Button verifybtn;
    private TextView verifyerror;
    private String mVerificationId;

    FirebaseAuth mAuth;
    RegData regData;
    private DatabaseReference ref;

    ProgressBar progressBar;

    String name,phoneno,email,password,verifycode;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_register);

        verifybtn = (Button)findViewById(R.id.verify_4dig_btn);
        verifytxt = (EditText)findViewById(R.id.verify_code);
        verifyerror = (TextView)findViewById(R.id.verify_errormsg);
        progressBar = (ProgressBar)findViewById(R.id.verify_progress);

        mAuth = FirebaseAuth.getInstance();
        regData = new RegData();
        ref = FirebaseDatabase.getInstance().getReference().child("Users");

        intent1 = getIntent();
        name = intent1.getStringExtra("name");
        phoneno = intent1.getStringExtra("phoneno");
        email = intent1.getStringExtra("email");
        password = intent1.getStringExtra("password");

        sendVerificationCode(phoneno);

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
                String code = verifytxt.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    verifyerror.setError("Enter valid code");
                    verifytxt.requestFocus();
                    return;
                }
                regNewUser();
                intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);

            }

/*                verifyVerificationCode(code);
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(VerifyRegister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(VerifyRegister.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    verifyerror.setText("Authentication failed!");
                                } else {

                                    startActivity(new Intent(VerifyRegister.this, Login.class));
                                    finish();
                                }
                            }
                        });

            }*/
        });

    }



    private void sendVerificationCode(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+94" + phone,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                verifytxt.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

    @Override
    public void onVerificationFailed(FirebaseException e) {
        Toast.makeText(VerifyRegister.this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        super.onCodeSent(s, forceResendingToken);
        mVerificationId = s;
    }
};

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        //signInWithPhoneAuthCredential(credential);
    }


    public void regNewUser(){
        //   progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(VerifyRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(VerifyRegister.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(VerifyRegister.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String id = mAuth.getCurrentUser().getUid();
                            Toast.makeText(VerifyRegister.this,id,Toast.LENGTH_SHORT).show();

                            regData.setUserName(name);
                            regData.setUserEmail(email);
                            regData.setUserPhone(phoneno);
                            regData.setUserPassword(password);
                            ref.child(id).setValue(regData);

                        }
                    }
                });

    }


    /*
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //verification successful we will start the profile activity
                            Intent intent = new Intent(VerifyRegister.this,Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();
                        }
                    }
                });

    }
*/
}
