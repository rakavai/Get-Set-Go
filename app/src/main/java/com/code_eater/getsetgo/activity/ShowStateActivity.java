package com.code_eater.getsetgo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code_eater.getsetgo.MainActivity;
import com.code_eater.getsetgo.R;

import utility.alarm.AlarmOption;
import utility.alarm.AlarmSetter;
import utility.data.type.DataState;
import utility.data.type.StateDataHandler;
import utility.notification.NotificationStatus;
import utility.ui.UiHandle;

public class ShowStateActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView subIcon;
    TextView stateTitle;
    TextView timeRemaining;
    TextView subtitle;
    AlarmOption alarmOption;
    DataState dataState;
    FloatingActionButton cancelButton;
    long goesOffTime;

    ShowStateActivity thisActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_state);
        init();
    }


    private void init() {
        UiHandle.setStatusBarTranslucent(this, true);
        initiateVariable();
        setDynamic();
        setCounter();
        initiateListener();
    }

    private void setDynamic() {
        initiateAlarmVariable();
        setRelatedView();
    }

    private void setRelatedView() {
        stateTitle.setText(Html.fromHtml(dataState.populateTitle()));
        imageView.setImageResource(dataState.populateImage());
        subtitle.setText(Html.fromHtml(dataState.populateSubTitle()));
        int subImageCode = dataState.populateSubImage();
        if (subImageCode > 0) {
            subIcon.setImageResource(subImageCode);
        }else{
            subIcon.setImageBitmap(null);
        }


    }

    private void setCounter() {

        new CountDownTimer(goesOffTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long timeDifference = goesOffTime - System.currentTimeMillis();
                if (timeDifference < 0) {
                    setDynamic();
                    return;
                }
                Long timeInMins = timeDifference / 60000;
                Long timeInSec = timeDifference / 1000 - timeInMins * 60;

                String formatedTime="";
                formatedTime+=timeInMins<10?"0"+timeInMins:timeInMins;
                formatedTime+=":";
                formatedTime+=timeInSec<10?"0"+timeInSec:timeInSec;

                timeRemaining.setText(
                        formatedTime
                );

            }

            @Override
            public void onFinish() {
                timeRemaining.setText("Times up!");
                /*Intent intent=getIntent();
                finish();
                startActivity(intent);*/
            }
        }.start();
    }

    private void initiateVariable() {
        stateTitle = (TextView) findViewById(R.id.alarm_state);
        timeRemaining = (TextView) findViewById(R.id.time_remaining);
        imageView = (ImageView) findViewById(R.id.stateImage);
        cancelButton = (FloatingActionButton) findViewById(R.id.cancel_the_alarm);
        subtitle = (TextView) findViewById(R.id.textSubtitle);
        subIcon = (ImageView) findViewById(R.id.imageSubIcon);
        thisActivity = this;
    }

    private void initiateAlarmVariable() {
        alarmOption = new AlarmOption(this);
        dataState = StateDataHandler.getRelatedDataType(alarmOption.getState());
        goesOffTime = alarmOption.getAlarmGoesOffTime();

    }

    public void initiateListener() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmSetter alarmSetter = new AlarmSetter(thisActivity);
                alarmSetter.cancelAlarm();
                new NotificationStatus(thisActivity).close();
                Intent mainIntent = new Intent(thisActivity, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}
