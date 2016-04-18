package utility.data.type;

import android.text.Html;
import android.text.Spanned;

import java.util.Random;

import utility.data.type.alldata.AllImageAndTitle;
import utility.data.type.alldata.AllQuotes;
import utility.data.type.alldata.ImageAndTitle;
import utility.data.type.alldata.Quote;

/**
 * Created by Rakibul Hasan on 1/4/2016.
 */
public class TextAndImage {
    public static String getWorkQuote(){
        Quote aQuote=AllQuotes.getRandomQuote();
        return "<P>"+aQuote.quote+"<br> -<strong>"+aQuote.author+"</strong></p>";

    }

    public static ImageAndTitle getImageAndTitle(){
        return AllImageAndTitle.getRandomImageAndTitle();
    }

}
