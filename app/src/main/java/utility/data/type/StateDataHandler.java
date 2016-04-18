package utility.data.type;

import utility.alarm.AlarmOption;

/**
 * Created by Rakibul Hasan on 12/13/2015.
 */
public class StateDataHandler {
    public static DataState getRelatedDataType(int state){
        if(state== AlarmOption.STATE_LONG_RELAX){
            return new DataForLongBreak();
        }
        if(state== AlarmOption.STATE_WORK){
            return new DataForWork();
        }

        return new DataForShortBreak();
    }
}
