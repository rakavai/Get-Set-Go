package com.code_eater.getsetgo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.code_eater.getsetgo.service.AlarmHandler;

import java.util.Calendar;

import utility.alarm.AlarmOption;
import utility.alarm.AlarmSetter;

public class TimerActivity extends AppCompatActivity {

    Button cancelButton;
    AlarmManager alarmManager;
    Intent showAlarmIntent;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setElement();
        setAlarmOption();
    }

    private void setAlarmOption(){
        AlarmOption alarmOption=new AlarmOption(this);
        if(alarmOption.hasAlarm()){

            return;
        }
        AlarmSetter alarmSetter=new AlarmSetter(this);
        alarmSetter.setAlarmTrue();
        alarmSetter.setAlarmAuto();
    }



    private void setElement(){
        final AlarmSetter alarmSetter=new AlarmSetter(this);
        cancelButton= (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmSetter.cancelAlarm();
            }
        });
    }


}
