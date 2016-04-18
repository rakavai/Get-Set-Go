package utility.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.code_eater.getsetgo.service.AlarmHandler;

import java.util.Calendar;

/**
 * Created by Rakibul Hasan on 12/11/2015.
 */
public class AlarmSetter {

    final int TIME_MULTIPLIER=60;
    Context context;
    AlarmManager alarmManager;
    Intent showAlarmIntent;
    PendingIntent pendingIntent;
    AlarmOption alarmOption;

    public AlarmSetter(Context context) {
        this.context = context;
        initiateVariable();
    }

    public void setAlarmTrue(){
        alarmOption.setAlarm();
    }


    public void setAlarmAfter(long inMin) {
        Calendar calendar = Calendar.getInstance();
        long settingTime = calendar.getTimeInMillis() + inMin * TIME_MULTIPLIER * 1000;
        setAlarmOnTime(settingTime);
    }

    public void setAlarmOnTime(long goesOffTime) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, goesOffTime, pendingIntent);
        alarmOption.setAlarmGoesOffTime(goesOffTime);
    }

    public void setWorkAlarm() {
        setAlarmAfter(25);
        alarmOption.setWorkSate();
        alarmOption.incrementCycle();

    }

    public void setRelaxAlarm() {
        setAlarmAfter(5);
        alarmOption.setRelaxState();
    }

    public void setLongRelaxAlarm() {
        setAlarmAfter(30);
        alarmOption.setStateLongRelax();
    }

    public void cancelAlarm() {
        alarmManager.cancel(pendingIntent);
        alarmOption.clearAlarm();
    }

    public void setAlarmAuto() {
        if(alarmOption.hasAlarm()==false){
            setAlarmTrue();
        }
        int alarmState = alarmOption.populateNextState();
        switch (alarmState) {
            case AlarmOption.STATE_WORK://end of work state
                setWorkAlarm();
                break;
            case AlarmOption.STATE_RELAX://end of relax state
                setRelaxAlarm();
                break;
            case AlarmOption.STATE_LONG_RELAX://end of long relax
                setLongRelaxAlarm();
                break;
        }
    }


    private void initiateVariable() {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        showAlarmIntent = new Intent(context, AlarmHandler.class);
        pendingIntent = PendingIntent.getService(context, 4563, showAlarmIntent, 0);
        alarmOption = new AlarmOption(context);
    }
}
