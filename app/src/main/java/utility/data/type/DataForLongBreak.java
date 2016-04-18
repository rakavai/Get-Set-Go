package utility.data.type;

import android.text.Html;

import com.code_eater.getsetgo.R;

/**
 * Created by Rakibul Hasan on 12/13/2015.
 */
public class DataForLongBreak implements DataState{

    @Override
    public String populateTitle() {
        return "<div style='text-align: center;'>Long Break.<br><small>Time to regain energy for the task.</small></div>";
    }

    @Override
    public int populateImage() {
        return R.drawable.therapy;
    }

    @Override
    public int populateSubImage() {
        return 0;
    }

    @Override
    public String populateSubTitle() {
        return "";
    }
}
