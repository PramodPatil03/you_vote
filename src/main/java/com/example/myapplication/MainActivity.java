package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    final static int READ_SIZE = 1000;
    private static final int PERMISSION_REQUEST_CODE = 1;

    String message = "Your vote is successfully counted \n\nThank you for Voting!!";

    EditText phoneNumberEditText;

    EditText messageEditText;
    Button sendButton;
    String Numbers[] = new String[READ_SIZE];
    public static final String EpicNum = "EpicNumbers.txt";
    EpicTypeCheck epicTypeCheck = new EpicTypeCheck();
    String epicnumbers = "";
    int flag = 0;
    EditText epic;
    public static final String FILE_NAME = "Votes1.txt";
    TextView showText;
    public int ch1 = 0, ch2 = 0, ch3 = 0, ch4 = 0, noch = 0;
    AppCompatButton vote, check;
    RadioButton btn1, btn2, btn3, btn4, nota;
    String choice;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vote = findViewById(R.id.Vote);
        vote.setEnabled(false);
        btn1 = findViewById(R.id.chOne);
        btn1.setEnabled(false);
        nota = findViewById(R.id.chNota);
        nota.setEnabled(false);
        btn2 = findViewById(R.id.chTwo);
        btn2.setEnabled(false);
        btn3 = findViewById(R.id.chThree);
        btn3.setEnabled(false);
        btn4 = findViewById(R.id.chFour);
        btn4.setEnabled(false);
        epic = findViewById(R.id.epicNum);
        check = findViewById(R.id.checkBtn);

        phoneNumberEditText = findViewById(R.id.Number);
        phoneNumberEditText.setEnabled(false);


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String epicNum = epic.getText().toString().trim();
                if (epicTypeCheck.checkPattern(epicNum)) {
                    try {
                        FileInputStream fis = openFileInput(EpicNum);
                        InputStreamReader isr = new InputStreamReader(fis);
                        char ch[] = new char[READ_SIZE];
                        StringBuilder sb = new StringBuilder();
                        int c;
                        while ((c = isr.read(ch)) > 0) {
                            String readString = String.copyValueOf(ch, 1, c);
                            epicnumbers = readString;
                            sb.append(readString);
                            ch = new char[READ_SIZE];
                            if (epicnumbers.contains(epicNum)) {
                                flag = 1;

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (flag == 1) {
                        flag = 0;
                        Toast.makeText(MainActivity.this, "You have already voted", Toast.LENGTH_SHORT).show();
                    } else {
                        epic.setText("");
                        epic.setEnabled(false);
                        check.setEnabled(false);
                        btn1.setEnabled(true);
                        btn2.setEnabled(true);
                        btn3.setEnabled(true);
                        btn4.setEnabled(true);
                        nota.setEnabled(true);
                        phoneNumberEditText.setEnabled(true);
                        vote.setTextColor(Color.BLACK);
                        try {
                            String data = epicnumbers + " " + epicNum;
                            FileOutputStream epicFos = openFileOutput(EpicNum, MODE_PRIVATE);
                            OutputStreamWriter epicOsw = new OutputStreamWriter(epicFos);
                            epicOsw.append(data);
                            epicOsw.flush();
                            epicOsw.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, "Make your vote thinkfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid Epic Number!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = phoneNumberEditText.getText().toString().trim();

                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},
                            PERMISSION_REQUEST_CODE);
                } else {
                    sendSMS(phoneNumber, message);
                }


                if (choice.equals("Party 1")) {
                    ch1 += 1;
                } else if (choice.equals("Party 2")) {
                    ch2 += 1;
                } else if (choice.equals("Party 3")) {
                    ch3 += 1;
                } else if (choice.equals("Party 4")) {
                    ch4 += 1;
                } else if (choice.equals("NOTA")) {
                    noch += 1;
                }
                String data = "PartyOne " + ch1 + "\nPartyTwo " + ch2 + "\nPartyThree " + ch3 + "\nPartyFour " + ch4 + "\nNoVote " + noch;

                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write(data);
                    osw.flush();
                    osw.close();
                    fos.close();
                    Toast.makeText(MainActivity.this, "Your Vote has been Saved, If you don't get a text message please inform to Authority!!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                vote.setEnabled(false);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                nota.setEnabled(false);
                phoneNumberEditText.setText("");
                phoneNumberEditText.setEnabled(false);
                check.setEnabled(true);
                btn1.setChecked(false);
                btn2.setChecked(false);
                btn3.setChecked(false);
                btn4.setChecked(false);
                nota.setChecked(false);
                epic.setEnabled(true);
                check.setEnabled(true);
                final MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.long_beep);
                mediaPlayer.start();
            }

        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote.setEnabled(true);
                choice = btn1.getText().toString();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote.setEnabled(true);
                choice = btn2.getText().toString();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote.setEnabled(true);
                choice = btn3.getText().toString();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote.setEnabled(true);
                choice = btn4.getText().toString();
            }
        });
        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote.setEnabled(true);
                choice = nota.getText().toString();
            }
        });
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String message = messageEditText.getText().toString().trim();
                sendSMS(phoneNumber, message);
            } else {
                Toast.makeText(MainActivity.this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}











