package utility.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.code_eater.getsetgo.MainActivity;
import com.code_eater.getsetgo.R;

/**
 * Created by Rakibul Hasan on 12/17/2015.
 */
public class NotificationStatus {
    public static int NOTI_ID = 234234234;
    NotificationManager notificationManager;
    Context context;
    NotificationCompat.Builder mBuilder;

    int icon;
    String contentTitle;
    String contentText;


    public NotificationStatus setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
        return this;
    }

    public NotificationStatus setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public NotificationStatus setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public NotificationStatus(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    public void close(){
        notificationManager.cancel(NOTI_ID);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notifyNow() {
        Uri sound = Uri.parse("android.resource://"
                + context.getPackageName() + "/" + R.raw.ding);
        mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(icon)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSound(sound)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH);
        Intent theIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(theIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        notificationManager.notify(NOTI_ID, mBuilder.build());
    }


}
