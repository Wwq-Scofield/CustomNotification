package com.zhs.notification.library;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by Administrator on 2017/10/18.
 */

public class CustomNotificationManager {
    public static NotificationManager notificationManager;

    /**
     * 此方法如果要加载网络图片，需要在主线程调用（因为推送实在IntentSerice里的）
     * 或者默认直接在主线程调用
     * @param context
     * @param mIntent
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void showNotification(Context context, Intent mIntent,int notifyId) {
        RemoteViews mRemoteView = new RemoteViews(context.getPackageName(), R.layout.layout_notification);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendhomeIntent = PendingIntent.getActivity(context, notifyId, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteView.setOnClickPendingIntent(R.id.root, pendhomeIntent);
        Notification.Builder builder1 = generateNotification(context, new Notification.Builder(context));
        builder1.setContentIntent(pendhomeIntent);
        Notification notification = builder1.build();
        notification.bigContentView = mRemoteView;
        notification.contentView = mRemoteView;
        mRemoteView.setTextViewText(R.id.tv_main_title, "标题");
        mRemoteView.setTextViewText(R.id.tv_subtitle, "内容");
        //TODO Glide 加载网络图片
//            NotificationTarget notificationTarget = new NotificationTarget(
//                    getApplicationContext(),
//                    mRemoteView,
//                    R.id.imageView,
//                    notification,
//                    notifyId);
//            String imgurl = mediaUrl;
//            Log.d("wwq", "imgurl: " + imgurl);
//            if (!TextUtils.isEmpty(imgurl)) {

//                Glide.with(getApplicationContext()) // safer!
//                        .load(imgurl)
//                        .asBitmap()
//                        .into(notificationTarget);
//            }
//        } catch (Exception e) {
//            Log.d("wwq", "e:  " + e.getLocalizedMessage());
//        }
        notificationManager.cancel(notifyId);
        notificationManager.notify(notifyId, notification);
    }

    private static Notification.Builder generateNotification(Context context, Notification.Builder builder) {
        Notification.Builder builder1 = builder.setSmallIcon(R.drawable.close_img)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setTicker("新消息");
        return builder1;
    }
    public static void dismiss(int notifyId) {
        if (notificationManager != null) {
            notificationManager.cancel(notifyId);
        }
    }
}
