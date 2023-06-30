package com.example.movieapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.audiofx.EnvironmentalReverb;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView userNameTextView;
    private EditText passWordEditText;
    private Button logInButton;
    ProgressBar simpleProgressBar;

    int counter = 0;

    private String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();


    }
    public void initViews(){
        userNameTextView = findViewById(R.id.et_user_name);
        passWordEditText = findViewById(R.id.et_PassWord);
        logInButton = findViewById(R.id.btn_button);
        simpleProgressBar = findViewById(R.id.simpleProgressBar);
    }
    public  void initListeners() {
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                simpleProgressBar.setVisibility(View.VISIBLE);

                Timer myTimer = new Timer();
                TimerTask myTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                           counter++;
                        simpleProgressBar.setProgress(counter);

                        if(counter == 30){
                            myTimer.cancel();
                        }

                    }
                };
                myTimer.schedule(myTimerTask, 30, 30);
                    if(ValidateupassWord()){
                        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                        savaData();
                        startActivity(intent);
                    }
            }
        });
        loadData();
        updateData();

    }
    public void savaData() {
        SharedPreferences peterSharedPreferences = getSharedPreferences("SharedPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = peterSharedPreferences.edit();
        myEditor.putString(ConstantMi.passWord,passWordEditText.getText().toString());
        myEditor.apply();
    }
    public void loadData() {
        SharedPreferences peterSharedPreferences = getSharedPreferences("SharedPreferences",Context.MODE_PRIVATE);
        passWord = peterSharedPreferences.getString(ConstantMi.passWord,"");


    }
    public void updateData() {

        passWordEditText.setText(passWord);
    }

    private boolean ValidateupassWord() {
        String passwordInput = passWordEditText.getText().toString().trim();
        String acceptablePassword = "^([a-zA-Z&$+._\\d]*)$";
        if (passwordInput.isEmpty()) {
            passWordEditText.setError("field cant be empty");
            return false;
        }

        if (passwordInput.length() < 6) {
            passWordEditText.setError("invalid password");
            return false;
        }
        if (!passwordInput.matches(acceptablePassword)) {
            passWordEditText.setError("incorrect input typed");
            return false;
        }
        return true;

     }

 }





