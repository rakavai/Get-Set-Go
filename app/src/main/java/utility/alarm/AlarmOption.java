package utility.alarm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Locale;

import utility.data.type.DataState;

/**
 * Created by Rakibul Hasan on 12/9/2015.
 */
public class AlarmOption {

    private static final String PREFERENCE_NAME = "ALARM_OPTION";
    private static final String IS_Alarm = "is alarm";
    private static final String ALARM_GOES_OFF_VALUE = "alarm goes off";
    private static final String ALARM_STATE = "state";
    private static final String CYCLE = "cycle";


    public static final int STATE_WORK = 1;
    public static final int STATE_RELAX = 0;
    public static final int STATE_LONG_RELAX = 2;


    private Context theActivity;
    private SharedPreferences alarmOptions;

    public AlarmOption(Context activity) {
        theActivity = activity;
        alarmOptions = theActivity.getSharedPreferences(PREFERENCE_NAME, 0);
    }


    private SharedPreferences.Editor storageEditor() {
        return alarmOptions.edit();
    }

    public void setAlarm() {
        storageEditor().putBoolean(IS_Alarm, true).commit();
    }

    public void unsetAlarm() {
        storageEditor().putBoolean(IS_Alarm, false);
    }

    public void setAlarmWithTime(long after) {
        Calendar calendar = Calendar.getInstance();
        setAlarm();
        setAlarmGoesOffTime(calendar.getTimeInMillis() + after);
    }


    public boolean hasAlarm() {
        return alarmOptions.getBoolean(IS_Alarm, false);
    }

    public void setAlarmGoesOffTime(long time) {
        storageEditor().putLong(ALARM_GOES_OFF_VALUE, time).commit();
    }

    public long getAlarmGoesOffTime() {
        return alarmOptions.getLong(ALARM_GOES_OFF_VALUE, 0);
    }


    public int getState() {
        return alarmOptions.getInt(ALARM_STATE, -1);
    }

    public int populateNextState() {

        switch (getState()) {
            case STATE_WORK://end of work state
                if (getCycle() % 4 == 0) {
                    return STATE_LONG_RELAX;
                }
                return STATE_RELAX;
            case STATE_RELAX://end of relax state
                return STATE_WORK;
            case STATE_LONG_RELAX://end of long relax
                return STATE_WORK;
        }
        return STATE_WORK;
    }


    public void setState(int state) {
        storageEditor().putInt(ALARM_STATE, state).commit();
    }

    public void setWorkSate() {
        setState(STATE_WORK);
    }

    public void setRelaxState() {
        setState(STATE_RELAX);
    }

    public void setStateLongRelax() {
        setState(STATE_LONG_RELAX);
    }


    public int getCycle() {
        return alarmOptions.getInt(CYCLE, 0);
    }

    public void setCycle(int cycle) {
        storageEditor().putInt(CYCLE, cycle).commit();
    }

    public void incrementCycle() {
        setCycle(getCycle() + 1);

    }

    public void clearAlarm() {
        storageEditor().clear().commit();
    }


}
