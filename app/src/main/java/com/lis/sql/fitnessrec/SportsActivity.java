package com.lis.sql.fitnessrec;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Administrator on 2017/12/10.
 */

public class SportsActivity extends Activity implements View.OnClickListener {
    private Timer timer;
    private Date currentTime;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
            String dateString = formatter.format(currentTime);
            textOclock.setText(dateString);
            super.handleMessage(msg);
        }
    };

    private Thread timeThread = new Thread() {
        @Override
        public void run() {
            while (true) {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private TextView textOclock;
    private Button btnStart;
    private Button btnFine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        textOclock = (TextView) findViewById(R.id.textOclock);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnFine = (Button) findViewById(R.id.btnFine);
        btnStart.setVisibility(View.VISIBLE);
        btnFine.setVisibility(View.GONE);
        btnStart.setOnClickListener(this);
        btnFine.setOnClickListener(this);
        timeThread.start();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnStart:
                btnFine.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                break;
            case R.id.btnFine:
                btnStart.setVisibility(View.VISIBLE);
                btnFine.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}

