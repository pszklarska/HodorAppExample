package pl.droidsonroids.hodor.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import pl.droidsonroids.hodor.BuildConfig;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.model.FCMMessage;
import pl.droidsonroids.hodor.ui.MainActivity;

public class HodorMessagingService extends FirebaseMessagingService {

    public static final String BUNDLE_REQUEST_CODE = BuildConfig.APPLICATION_ID + ".request_code";
    public static final String BUNDLE_USERNAME = BuildConfig.APPLICATION_ID + ".username";
    public static final int REQUEST_CODE_SEND_HODOR_BACK = 105;

    private static final int NOTIFICATION_ID = 103;
    private static final String URI_ANDROID_RESOURCE = "android.resource://";

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        String username = remoteMessage.getData().get(FCMMessage.USERNAME);

        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent mainActivityPendingIntent =
                PendingIntent.getActivity(this, (int) System.currentTimeMillis(), mainActivityIntent, 0);

        Intent mainActivityWithActionIntent = new Intent(this, MainActivity.class);
        mainActivityWithActionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainActivityWithActionIntent.putExtra(BUNDLE_REQUEST_CODE, REQUEST_CODE_SEND_HODOR_BACK);
        mainActivityWithActionIntent.putExtra(BUNDLE_USERNAME, username);
        PendingIntent mainActivityWithActionPendingIntent =
                PendingIntent.getActivity(this, (int) System.currentTimeMillis(), mainActivityWithActionIntent, 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification)
                        .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .setContentText(getString(R.string.notification_msg, username))
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentIntent(mainActivityPendingIntent)
                        .addAction(0, getString(R.string.send_hodor_back), mainActivityWithActionPendingIntent)
                        .setSound(Uri.parse(URI_ANDROID_RESOURCE + getPackageName() + "/" + R.raw.hodor));

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}
