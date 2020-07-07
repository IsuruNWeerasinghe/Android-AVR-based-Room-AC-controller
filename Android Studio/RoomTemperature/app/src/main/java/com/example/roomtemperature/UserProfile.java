package com.example.roomtemperature;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    private Toolbar toolbar;

    private TextView userName;
    private TextView userEmail;
    private TextView userPhone;
    private TextView userPassword;
    private Button editName;
    private Button editEmail;
    private Button editPhone;
    private Button editPassword;
    private Button deleteAccount;
    private String Name,Email,Phone,Password;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public UserProfile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = (Toolbar)findViewById(R.id.usertoolbar);
        userName =(TextView)findViewById(R.id.profileName);
        userEmail =(TextView)findViewById(R.id.profileEmail);
        userPhone =(TextView)findViewById(R.id.profilePhone);
        userPassword =(TextView)findViewById(R.id.profilePassword);
        editName = (Button)findViewById(R.id.profileNameBtn);
        editEmail = (Button)findViewById(R.id.profileEmailBtn);
        editPhone = (Button)findViewById(R.id.profilePhoneBtn);
        editPassword = (Button)findViewById(R.id.profilePasswordBtn);
        deleteAccount = (Button)findViewById(R.id.deleteaccount);

        toolbar.setTitle("User Profile");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.dialog_rl);
        final TextView editpasswordtxt = (TextView) findViewById(R.id.editpwtxt);
        final TextView editrepasswordtxt = (TextView) findViewById(R.id.editrepwtxt);

        auth = FirebaseAuth.getInstance();
        user = auth.getInstance().getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SetUserCurrentDetails(auth.getUid());

        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword(auth.getUid());
            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeEmail(auth.getUid());
            }
        });

        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePhone(auth.getUid());
            }
        });

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeName(auth.getUid());
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAccount();
            }
        });

    }

    private void ChangeName(String uid){
        reference = database.getReference("Users").child(uid);

        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_name,null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
        Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
        final EditText et_name = (EditText) dialogView.findViewById(R.id.editnametxt);
        final AlertDialog dialog = builder.create();

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                String name = et_name.getText().toString();
                reference.child("userName").setValue(name);
                Toast.makeText(getApplicationContext(),"User name changed to " + name, Toast.LENGTH_SHORT).show();

            }
        });

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss/cancel the alert dialog
                //dialog.cancel();
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    private void ChangeEmail(String uid){
        reference = database.getReference("Users").child(uid);

        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_mail,null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
        Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
        final EditText et_email = (EditText) dialogView.findViewById(R.id.editemailtxt);
        final AlertDialog dialog = builder.create();

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                String newEmail = et_email.getText().toString();
                Toast.makeText(getApplicationContext(),"User Email changed to " + newEmail, Toast.LENGTH_SHORT).show();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updateEmail(et_email.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    reference.child("userEmail").setValue(et_email.getText().toString());
                                    Toast.makeText(UserProfile.this, "Email address is updated.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(UserProfile.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss/cancel the alert dialog
                //dialog.cancel();
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    private void ChangePhone(String uid){
        reference = database.getReference("Users").child(uid);
        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_phone,null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
        Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
        final EditText et_phone = (EditText) dialogView.findViewById(R.id.editphonetxt);
        final AlertDialog dialog = builder.create();

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                String phone = et_phone.getText().toString();
                reference.child("userPhone").setValue(phone);
                Toast.makeText(getApplicationContext(),"User phone changed to " + phone, Toast.LENGTH_SHORT).show();

            }
        });

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss/cancel the alert dialog
                //dialog.cancel();
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    private void ChangePassword(String uid){
        reference = database.getReference("Users").child(uid);

        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_password,null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
        Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
        final TextView errormsg = (TextView) dialogView.findViewById(R.id.editpwerror);
        final EditText et_password = (EditText) dialogView.findViewById(R.id.editpwtxt);
        final EditText et_repassword = (EditText) dialogView.findViewById(R.id.editrepwtxt);
        final AlertDialog dialog = builder.create();

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                final String password = et_password.getText().toString();
                String repassword = et_repassword.getText().toString();
                //Toast.makeText(getApplicationContext(),"Submitted name : " + password, Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter password!", Toast.LENGTH_SHORT).show();

                }else if (password.length() < 6){
                    Toast.makeText(getApplicationContext(),"Password must contain at least 6 characters!", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(repassword)) {
                    Toast.makeText(getApplicationContext(),"Enter re-password!", Toast.LENGTH_SHORT).show();

                }else if ((password.equals(repassword))== false){
                    Toast.makeText(getApplicationContext(),"Passwords not match!", Toast.LENGTH_SHORT).show();

                }else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updatePassword(password)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        reference.child("userPassword").setValue(password);
                                        Toast.makeText(UserProfile.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(UserProfile.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                        //progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }

            }
        });

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss/cancel the alert dialog
                //dialog.cancel();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void DeleteAccount(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(UserProfile.this);
        builder1.setMessage("Are you sure want to delete user account?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            user.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(UserProfile.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(UserProfile.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void SetUserCurrentDetails(String uid){
        reference = database.getReference("Users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Name = dataSnapshot.child("userName").getValue(String.class);
                Email  = dataSnapshot.child("userEmail").getValue(String.class);
                Phone = dataSnapshot.child("userPhone").getValue(String.class);
                Password = dataSnapshot.child("userPassword").getValue(String.class);
                //Toast.makeText(getApplicationContext(),Name,Toast.LENGTH_SHORT).show();

                userName.setText(Name);
                userEmail.setText(Email);
                userPhone.setText(Phone);
                userPassword.setText(Password);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e(TAG, "Failed to read app title value.", databaseError.toException());
            }
        });
    }
}
