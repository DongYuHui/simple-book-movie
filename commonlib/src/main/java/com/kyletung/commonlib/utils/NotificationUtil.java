package com.kyletung.commonlib.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/24 at 18:23<br>
 * Notification Util
 */
public class NotificationUtil {

    private static final int NOTIFY_ID = 7;

    public static void showDefault(
            Context context,
            @NonNull String title,
            @NonNull String content,
            int smallIcon,
            @Nullable Bitmap largeIcon,
            @Nullable OnGetIntent onGetIntent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(content);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(smallIcon);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        if (largeIcon != null) builder.setLargeIcon(largeIcon);
        if (onGetIntent != null) builder.setFullScreenIntent(onGetIntent.getIntent(), true);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID, builder.build());
    }

    public interface OnGetIntent {
        PendingIntent getIntent();
    }

}
