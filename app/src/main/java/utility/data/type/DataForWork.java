package utility.data.type;

import android.text.Html;

import com.code_eater.getsetgo.R;

/**
 * Created by Rakibul Hasan on 12/13/2015.
 */
public class DataForWork implements DataState {
    @Override
    public String populateTitle() {
        return "<div style='text-align: center;'>Keep Working<br><small>You are going to be great</small></div>";
    }

    @Override
    public int populateImage() {
        return R.drawable.work;
    }

    @Override
    public int populateSubImage() {
        return R.drawable.quote;
    }

    @Override
    public String populateSubTitle() {
        return TextAndImage.getWorkQuote();
    }


}
