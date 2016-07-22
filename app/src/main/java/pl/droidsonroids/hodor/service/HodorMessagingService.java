package pl.droidsonroids.hodor.service;

import android.app.NotificationManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.model.FCMMessage;

public class HodorMessagingService extends FirebaseMessagingService {

    private static final int NOTIFICATION_ID = 103;
    public static final String URI_ANDROID_RESOURCE = "android.resource://";

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification)
                        .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .setContentText(getString(R.string.notification_msg,
                                remoteMessage.getData().get(FCMMessage.USERNAME)))
                        .setContentTitle(getString(R.string.notification_title))
                        .setSound(Uri.parse(URI_ANDROID_RESOURCE + getPackageName() + "/" + R.raw.hodor));

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}
