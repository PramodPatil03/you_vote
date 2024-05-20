package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class AdminPage extends AppCompatActivity {
    private static final int READ_SIZE = 500;
    TextView showText,win1,win2,win3,win4,firstPlace,secondPlace,thirdPlace,fourthPlace;
    String Str,finalStr;
   int Array[];
   KonfettiView konfettiView;

AppCompatButton load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        showText = findViewById(R.id.showText);
        win1 = findViewById(R.id.first);
        win2 = findViewById(R.id.second);
        win3 = findViewById(R.id.third);
        win4 = findViewById(R.id.fourth);
        firstPlace = findViewById(R.id.firstPlace);
        secondPlace = findViewById(R.id.secondPlace);
        thirdPlace = findViewById(R.id.thirdPlace);
        fourthPlace = findViewById(R.id.fourthPlace);
        konfettiView = findViewById(R.id.konfettiView);
        Array = new int[5];
    }
    public void load(View view) {try {
        FileInputStream fis = openFileInput("Votes1.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        char ch[] = new char[READ_SIZE];
        StringBuilder sb = new StringBuilder();
        int c;
        while((c= isr.read(ch))>0){
            String readString = String.copyValueOf(ch,0,c);
            sb.append(readString);
            ch = new char[READ_SIZE];
        }
        Str = sb.toString();
        Toast.makeText(AdminPage.this, "Information fetched Successfully", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    finally {
        String txt = Str.replaceAll("\n", " ");
        String fnl = txt.replaceAll(" ", "");

        int i = 0;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(fnl);
        while (m.find()) {
            Array[i] = Integer.parseInt(m.group());
            i++;
        }
        if (Array[4] >= (Array[0] + Array[1] + Array[2] + Array[3])) {
            showText.setText("Re-Election according to the Election Commission rules, As all candidates are disqualified!!!!");
            return;
        } else {
            final MediaPlayer mediaPlayer = MediaPlayer.create(AdminPage.this, R.raw.firework);
            mediaPlayer.start();
        EmitterConfig emitterConfig = new Emitter(500, TimeUnit.MILLISECONDS).max(500);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .shapes(Shape.Circle.INSTANCE,Shape.Square.INSTANCE)
                        .spread(360)
                        .position(0.2,0.2,1,1)
                        .sizes(new Size(8,50,10))
                        .timeToLive(5000).fadeOutEnabled(true).build()
        );



                showText.setText(Str);
                win1.setText("" + Array[0]);
                win2.setText("" + Array[1]);
                win3.setText("" + Array[2]);
                win4.setText("" + Array[3]);
                int max1 = Math.max(Array[0], Array[1]);
                int max2 = Math.max(Array[2], Array[3]);
                int max = Math.max(max1, max2);
                if (Integer.parseInt(win1.getText().toString()) == max && Integer.parseInt(win2.getText().toString()) == max) {
                    firstPlace.setText("Tie");
                    secondPlace.setText("Tie");
                    thirdPlace.setText("loss");
                    fourthPlace.setText("loss");

                    firstPlace.setTextColor(Color.BLACK);
                    secondPlace.setTextColor(Color.BLACK);
                    thirdPlace.setTextColor(Color.RED);
                    fourthPlace.setTextColor(Color.RED);
                    return;
                }
                if (Integer.parseInt(win1.getText().toString()) == max && Integer.parseInt(win3.getText().toString()) == max) {
                    firstPlace.setText("Tie");
                    secondPlace.setText("Loss");
                    thirdPlace.setText("Tie");
                    fourthPlace.setText("Loss");

                    firstPlace.setTextColor(Color.BLACK);
                    secondPlace.setTextColor(Color.RED);
                    thirdPlace.setTextColor(Color.BLACK);
                    fourthPlace.setTextColor(Color.RED);
                    return;
                }
                if (Integer.parseInt(win1.getText().toString()) == max && Integer.parseInt(win4.getText().toString()) == max) {
                    firstPlace.setText("Tie");
                    secondPlace.setText("Loss");
                    thirdPlace.setText("Loss");
                    fourthPlace.setText("Tie");

                    firstPlace.setTextColor(Color.BLACK);
                    secondPlace.setTextColor(Color.RED);
                    thirdPlace.setTextColor(Color.RED);
                    fourthPlace.setTextColor(Color.BLACK);
                    return;
                }

                if (Integer.parseInt(win2.getText().toString()) == max && Integer.parseInt(win3.getText().toString()) == max) {
                    firstPlace.setText("Loss");
                    secondPlace.setText("Tie");
                    thirdPlace.setText("Tie");
                    fourthPlace.setText("Loss");

                    firstPlace.setTextColor(Color.RED);
                    secondPlace.setTextColor(Color.BLACK);
                    thirdPlace.setTextColor(Color.BLACK);
                    fourthPlace.setTextColor(Color.RED);
                    return;
                }

                if (Integer.parseInt(win2.getText().toString()) == max && Integer.parseInt(win4.getText().toString()) == max) {
                    firstPlace.setText("Loss");
                    secondPlace.setText("Tie");
                    thirdPlace.setText("Loss");
                    fourthPlace.setText("Tie");

                    firstPlace.setTextColor(Color.RED);
                    secondPlace.setTextColor(Color.BLACK);
                    thirdPlace.setTextColor(Color.RED);
                    fourthPlace.setTextColor(Color.BLACK);
                    return;
                }

                if (Integer.parseInt(win3.getText().toString()) == max && Integer.parseInt(win4.getText().toString()) == max) {
                    firstPlace.setText("Loss");
                    secondPlace.setText("Loss");
                    thirdPlace.setText("Tie");
                    fourthPlace.setText("Tie");

                    firstPlace.setTextColor(Color.RED);
                    secondPlace.setTextColor(Color.RED);
                    thirdPlace.setTextColor(Color.BLACK);
                    fourthPlace.setTextColor(Color.BLACK);
                    return;
                }

                if (Integer.parseInt(win1.getText().toString()) == max) {
                    firstPlace.setText("Win");
                    secondPlace.setText("Lost");
                    thirdPlace.setText("Lost");
                    fourthPlace.setText("Lost");
                    firstPlace.setTextColor(Color.GREEN);
                    secondPlace.setTextColor(Color.RED);
                    thirdPlace.setTextColor(Color.RED);
                    fourthPlace.setTextColor(Color.RED);
                }
                if (Integer.parseInt(win2.getText().toString()) == max) {
                    firstPlace.setText("Lost");
                    secondPlace.setText("Win");
                    thirdPlace.setText("Lost");
                    fourthPlace.setText("Lost");
                    firstPlace.setTextColor(Color.RED);
                    secondPlace.setTextColor(Color.GREEN);
                    thirdPlace.setTextColor(Color.RED);
                    fourthPlace.setTextColor(Color.RED);
                }
                if (Integer.parseInt(win3.getText().toString()) == max) {
                    firstPlace.setText("Lost");
                    secondPlace.setText("Lost");
                    thirdPlace.setText("Win");
                    fourthPlace.setText("Lost");
                    firstPlace.setTextColor(Color.RED);
                    secondPlace.setTextColor(Color.RED);
                    thirdPlace.setTextColor(Color.GREEN);
                    fourthPlace.setTextColor(Color.RED);
                }
                if (Integer.parseInt(win4.getText().toString()) == max) {
                    firstPlace.setText("Lost");
                    secondPlace.setText("Lost");
                    thirdPlace.setText("Lost");
                    fourthPlace.setText("Win");
                    firstPlace.setTextColor(Color.RED);
                    secondPlace.setTextColor(Color.RED);
                    thirdPlace.setTextColor(Color.RED);
                    fourthPlace.setTextColor(Color.GREEN);
                }

            }
        }
    }

    public void goBack(View view) {
        Intent intent4 =  new Intent(AdminPage.this,Home_Screen.class);
        startActivity(intent4);
    }
}