package com.code_eater.getsetgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.code_eater.getsetgo.activity.ShowStateActivity;

import utility.alarm.AlarmOption;
import utility.alarm.AlarmSetter;
import utility.notification.HelpDialog;
import utility.ui.UiHandle;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    private MainActivity thisActivity = this;
    private AlarmOption alarmOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkAlarm();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initiate();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initiate() {
       initiateElement();
        setListener();

    }

    private void checkAlarm() {

        alarmOption = new AlarmOption(this);
        if (alarmOption.hasAlarm()) {
            Intent alarmState = new Intent(this, ShowStateActivity.class);
            startActivity(alarmState);
            finish();
        }
    }

    private void initiateElement() {
        startButton = (Button) findViewById(R.id.start_button);
        alarmOption = new AlarmOption(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final String toShow = String.valueOf(alarmOption.hasAlarm())
                .concat(" ")
                .concat(String.valueOf(alarmOption.getAlarmGoesOffTime()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HelpDialog(thisActivity).show();
            }
        });

    }

    private void setListener() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmSetter alarmSetter = new AlarmSetter(thisActivity);
                alarmSetter.setAlarmAuto();
                checkAlarm();
            }
        });
    }
}
