package utility.data.type.alldata;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Rakibul Hasan on 1/4/2016.
 */
public class AllQuotes {
    public final static ArrayList<Quote> allQuotes = new ArrayList<>();

    static {
        Quote[] quotesArray = {
                new Quote("The best preparation for good work tomorrow is to do good work today.", "Elbert Hubbard"),
                new Quote("Nothing will work unless you do.", "Maya Angelou"),
                new Quote("The only place success comes before work is in the dictionary.", "Vince Lombardi")
        };
        allQuotes.addAll(Arrays.asList(quotesArray));
    }

    public static Quote getRandomQuote(){
        Random ran = new Random();
        int x = ran.nextInt(AllQuotes.allQuotes.size());
        return allQuotes.get(x);
    }



}
