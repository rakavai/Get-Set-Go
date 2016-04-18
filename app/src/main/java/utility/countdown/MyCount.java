package utility.countdown;

import android.os.CountDownTimer;

/**
 * Created by Rakibul Hasan on 12/12/2015.
 */
public class MyCount extends CountDownTimer {

    ToDo toDo;
    public MyCount(long millisInFuture, long countDownInterval, ToDo toDo) {
        super(millisInFuture, countDownInterval);
        this.toDo=toDo;
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onTick(long millisUntilFinished) {
        toDo.print();
    }
}
