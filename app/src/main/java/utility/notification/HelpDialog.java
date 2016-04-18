package utility.notification;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.code_eater.getsetgo.R;

/**
 * Created by Rakibul Hasan on 12/16/2015.
 */
public class HelpDialog extends Dialog {

    HelpDialog thisDialog;
    public HelpDialog(Context context) {
        super(context);

        thisDialog=this;

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_layout);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setVisibility(View.GONE);
        webView.loadUrl("file:///android_asset/html/text.html");

        webView.reload();
        webView.setVisibility(View.VISIBLE);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        webView.setWebViewClient(new WebViewClient());


        //
        Button endDialog= (Button) findViewById(R.id.endDialog);
        endDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDialog.dismiss();
            }
        });

    }
}
