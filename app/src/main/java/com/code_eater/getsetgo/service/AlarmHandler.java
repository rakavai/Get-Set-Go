package com.code_eater.getsetgo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;

import android.os.Handler;

import utility.alarm.AlarmOption;
import utility.alarm.AlarmSetter;
import utility.data.type.DataState;
import utility.data.type.StateDataHandler;
import utility.notification.NotificationStatus;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class AlarmHandler extends IntentService {

    public AlarmHandler() {
        super("AlarmHandler");
    }

    private AlarmHandler thisContext = this;
    String alarmStatus = "";
    int alarmState;
    AlarmOption alarmOption;
    @Override
    protected void onHandleIntent(Intent intent) {
        alarmOption = new AlarmOption(this);
        alarmState = alarmOption.populateNextState();
        switch (alarmState) {
            case AlarmOption.STATE_WORK://end of work state
                alarmStatus = "Now its working time";
                break;
            case AlarmOption.STATE_RELAX://end of relax state
                alarmStatus = "You can relax now for  5 min";
                break;
            case AlarmOption.STATE_LONG_RELAX://end of long relax
                alarmStatus = "Take a long time for relaxing";
                break;
        }
        final String toShow = alarmStatus;
        final AlarmSetter alarmSetter = new AlarmSetter(this);
        alarmSetter.setAlarmAuto();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                DataState dataState = StateDataHandler.getRelatedDataType(alarmState);
                new NotificationStatus(thisContext)
                        .setIcon(dataState.populateImage())
                        .setContentText(alarmStatus)
                        .setContentTitle("Get Set Go")
                        .notifyNow();
            }
        });


    }
}
