package com.yhkhgl.top.push;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class NotificationsUtils {
    public static void showNotification(String title) {
        final int NOTIFICATION_ID = 12234;

//        NotificationManager notificationManager = (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        //准备intent
//        Intent intent = new Intent();
//        String action = "com.tamic.myapp.action";
//        intent.setAction(action);
//
//        //notification
//        Notification notification = null;
//        String contentText = null;
//        // 构建 PendingIntent
//        PendingIntent pi = PendingIntent.getActivity(App.getContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        //版本兼容
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//            notification = new Notification();
//            notification.icon = R.mipmap.logo;
//            notification.flags |= Notification.FLAG_AUTO_CANCEL;
////            notification.setLatestEventInfo(App.getContext(), aInfo.mFilename, contentText, pi);
//
//        } else if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O && Build.VERSION.SDK_INT >= LOLLIPOP_MR1) {
//            notification = new NotificationCompat.Builder(App.getContext())
//                    .setContentTitle(title)
//                    .setContentText(contentText)
//                    .setSmallIcon(R.mipmap.logo)
//                    .setContentIntent(pi).build();
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
//                Build.VERSION.SDK_INT <= LOLLIPOP_MR1) {
//            notification = new Notification.Builder(App.getContext())
//                    .setAutoCancel(false)
//                    .setContentIntent(pi)
//                    .setSmallIcon(R.mipmap.logo)
//                    .setWhen(System.currentTimeMillis())
//                    .build();
//        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//
//
//            String CHANNEL_ID = "my_channel_01";
//            CharSequence name = "my_channel";
//            String Description = "This is my channel";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//            mChannel.setDescription(Description);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.RED);
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            mChannel.setShowBadge(false);
//            notificationManager.createNotificationChannel(mChannel);
//
//            notification = new NotificationCompat.Builder(App.getContext(), CHANNEL_ID)
//                    .setSmallIcon(R.mipmap.logo)
//                    .setContentTitle(title).build();
//
//        }
//
//        notificationManager.notify(NOTIFICATION_ID, notification);

////启动应用
//        Intent launchIntent = App.getAppContext().getPackageManager().
//                getLaunchIntentForPackage("com.yhkhgl.top");
//        launchIntent.setFlags(
//                Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//        Bundle args = new Bundle();
//        startActivity(launchIntent);

    }
}
