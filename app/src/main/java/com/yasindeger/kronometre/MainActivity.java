package com.yasindeger.kronometre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView milisn;
    TextView saniye;
    TextView dakika;
    TextView saat;
    TextView laps;
    Button getlapsButton;
    Runnable runnable;
    Handler handler;
    int number;
    int ssaniye;
    int ddakika;
    int ssaat;
    Button startButton;
    Button stopButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        milisn = findViewById(R.id.ms);
        saniye = findViewById(R.id.sn);
        dakika = findViewById(R.id.dk);
        saat = findViewById(R.id.hr);
        startButton = findViewById(R.id.start);
        stopButton = findViewById(R.id.stop);
        getlapsButton = findViewById(R.id.button3);
        laps = findViewById(R.id.textView4);
        number = 0;
    }


    public void start(View view){

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                milisn.setText(""+number);
                number++;
                if(number == 99){
                    number %= 99;
                    milisn.setText("00");
                    ssaniye++;
                    if(ssaniye<10){
                        saniye.setText("0"+ssaniye);
                    }else{
                        saniye.setText(""+ssaniye);
                    }
                    if(ssaniye == 59){

                        ssaniye %=59;
                        saniye.setText("0"+ssaniye);
                        ddakika++;
                        if(ddakika<10){
                            dakika.setText("0"+ddakika);
                        }else{
                            dakika.setText(""+ddakika);
                        }

                        if(ddakika == 59){
                            ddakika%=59;
                            dakika.setText("0"+ddakika);
                            ssaat++;

                            if(ssaat<10){
                                saat.setText("0"+ssaat);
                            }else{
                                saat.setText(""+ssaat);
                            }
                        }

                    }


                }
                milisn.setText(""+number);
                handler.postDelayed(runnable,10);
                getData(ssaat,ddakika,ssaniye,number);
            }
        };
        handler.post(runnable);
        startButton.setEnabled(false);



    }


    public void stop(View view){

        startButton.setEnabled(true);
        handler.removeCallbacks(runnable);
        number = 0;
        milisn.setText("00");
        saniye.setText("00");
        dakika.setText("00");
        saat.setText("00");


    }

    private List<String> getData(int saat,int dakika,int saniye,int milisaniye){

        List<String> list = new ArrayList<>();

        list.add(String.valueOf(saat));
        list.add(String.valueOf(dakika));
        list.add(String.valueOf(saniye));
        list.add(String.valueOf(milisaniye));


        return  list;
    }


    public void getlap(View view){

        List<String> data = getData(ssaat, ddakika, ssaniye, number);
        String[] arr = new String[4];
        for (int i = 0; i < 4; i++) {
            if(data.get(i).length()==1){
                arr[i] = "0"+data.get(i);
            }else{
                arr[i] = data.get(i);
            }
        }

        laps.setText(arr[0]+":"+arr[1]+":"+arr[2]+":"+arr[3]);

    }
}