package utility.data.type.alldata;

import com.code_eater.getsetgo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Rakibul Hasan on 1/27/2016.
 */
public class AllImageAndTitle {
    final static List<ImageAndTitle> allImageAndTitle = new ArrayList<>();
    ImageAndTitle[] imageAndTitleArray;
    static {
        ImageAndTitle[] imageAndTitleArray = {
                new ImageAndTitle(R.drawable.water, "<p>Drink more water.<br>" +
                        "<small>Water is refreshing and healthy.</small></p>"),
                new ImageAndTitle(R.drawable.walking, "<p>Stand up.<br>" +
                        "<small>Taking a simple 5 minute break to walk around will remove the negative effects of sitting</small></p>")
        };
        allImageAndTitle.addAll(Arrays.asList(imageAndTitleArray));
    }

    public static ImageAndTitle getRandomImageAndTitle(){
        Random ran = new Random();
        int x = ran.nextInt(allImageAndTitle.size());
        return allImageAndTitle.get(x);
    }

}
