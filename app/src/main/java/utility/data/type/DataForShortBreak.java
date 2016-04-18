package utility.data.type;

import android.text.Html;

import com.code_eater.getsetgo.R;

import utility.data.type.alldata.ImageAndTitle;

/**
 * Created by Rakibul Hasan on 12/13/2015.
 */
public class DataForShortBreak implements DataState{


    ImageAndTitle imageAndTitle;

    public DataForShortBreak(){
        imageAndTitle=TextAndImage.getImageAndTitle();
    }

    @Override
    public String populateTitle() {
        return "<div style='text-align: center;'>Short break<br><small>its awesome!<small></div>";
    }

    @Override
    public int populateImage() {
        return R.drawable.short_break;
    }

    @Override
    public int populateSubImage() {
        return imageAndTitle.image;
    }

    @Override
    public String populateSubTitle() {
        return imageAndTitle.title;
    }
}
