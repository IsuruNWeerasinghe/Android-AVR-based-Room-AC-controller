package com.example.roomtemperature;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.view.textclassifier.TextClassification;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.model.ChannelFeed;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.client.Request;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mGetReference;

    private String userName,userEmail,userPhone,userId;;
    private TextView navUser,navEmail,navPhone;
    private TextView temptxt,humitxt,doortxt;
    private ToggleButton acbtn1,acbtn2;
    public static final int RequestPermissionCode =1;
    private Intent intent;

    private ThingSpeakChannel tsChannel;
    private Integer TsChannelID = 807302;
    private String doorstatus =null;
    private String acstatus1 = "on";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnableRuntimePermission();
        auth = FirebaseAuth.getInstance();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        navUser = (TextView)header.findViewById(R.id.navusername);
        navEmail = (TextView)header.findViewById(R.id.navuseremail);
        navPhone = (TextView)header.findViewById(R.id.navusertele);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        temptxt = (TextView)findViewById(R.id.temptxtbx);
        humitxt = (TextView)findViewById(R.id.humitxtbx);
        doortxt = (TextView)findViewById(R.id.doortxtbx);
        acbtn1 = (ToggleButton)findViewById(R.id.ac1button);
        acbtn2 = (ToggleButton)findViewById(R.id.ac2button);

        setSupportActionBar(toolbar);
        mDatabase = FirebaseDatabase.getInstance();
        mGetReference = mDatabase.getReference();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }

                userId = auth.getCurrentUser().getUid();
                //Toast.makeText(getApplicationContext(),userId,Toast.LENGTH_SHORT).show();

                NavHeaderDetails(userId);
                //UpdateData();
            }
        };

        ThingspeakDataBase();
        AcButtonClick();

    }


    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you need to exit from the App ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent exit = new Intent(Intent.ACTION_MAIN);
                        exit.addCategory(Intent.CATEGORY_HOME);
                        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(exit);
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }
    /*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    public void EnableRuntimePermission(){
        String[] permissions ={
                Manifest.permission.INTERNET
        };
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.INTERNET)){
            Toast.makeText(MainActivity.this,"Internet Permission allows us to Access Internet",Toast.LENGTH_LONG).show();
        }else {
            ActivityCompat.requestPermissions(MainActivity.this,permissions,RequestPermissionCode);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home_menu_icon) {
            // Handle the camera action
        } else if (id == R.id.history_menu_icon) {
            intent = new Intent(MainActivity.this, History.class);
            startActivity(intent);
        } else if (id == R.id.settings_menu_icon) {
            intent = new Intent(MainActivity.this,Service.class);
            startActivity(intent);
        } else if (id == R.id.about_menu_icon) {

        } else if (id == R.id.reg_menu_icon) {
            intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        } else if (id == R.id.myprofile_menu_icon) {
            intent = new Intent(MainActivity.this,UserProfile.class);
            startActivity(intent);
        } else if (id == R.id.login_menu_icon) {
            intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        } else if (id == R.id.logout_menu_icon) {

            signOut();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    public void NavHeaderDetails(String uid){
        mGetReference = mDatabase.getReference("Users").child(uid);
        mGetReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child("userName").getValue(String.class);
                userEmail  = dataSnapshot.child("userEmail").getValue(String.class);
                userPhone = dataSnapshot.child("userPhone").getValue(String.class);
                //Toast.makeText(getApplicationContext(),userName,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),userPhone,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),userEmail,Toast.LENGTH_SHORT).show();

                navUser.setText(userName);
                navEmail.setText(userEmail);
                navPhone.setText(userPhone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e(TAG, "Failed to read app title value.", databaseError.toException());
            }
        });
    }


    private void ThingspeakDataBase(){
        tsChannel = new ThingSpeakChannel(TsChannelID);
        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                Integer last = channelFeed.getFeeds().size();
                String temp = channelFeed.getFeeds().get(last-1).getField1();
                String humi = channelFeed.getFeeds().get(last-1).getField2();
                String door = channelFeed.getFeeds().get(last-1).getField3();

                if(door.equals("")){
                    doorstatus = "Open";
                }else if (door.equals("K")){
                    doorstatus = "Close";
                }
                temptxt.setText(temp+"\u00B0C");
                humitxt.setText(humi+ "%");
                doortxt.setText(doorstatus);
            }
        });
        tsChannel.loadChannelFeed();


    }
/*ToggleButton toggle = (ToggleButton) findViewById(R.id.togglebutton);
                toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {*/

    private void AcButtonClick(){
        acbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"AC1 not connected",Toast.LENGTH_LONG).show();
                if(acstatus1.equals("on")){
                    acstatus1 = "off";
                } else if (acstatus1.equals("off")){
                    acstatus1 = "on";
                }
            }
        });

        acbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"AC2 not connected",Toast.LENGTH_LONG).show();
            }
        });



    }


}



