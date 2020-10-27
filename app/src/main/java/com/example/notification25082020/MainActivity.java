package com.example.notification25082020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnNotification;
    String MY_CHANNEL = "MY_CHANNEL";
    NotificationManager mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNotification  = findViewById(R.id.buttonNotification);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mBtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notification =
                        new NotificationCompat.Builder(MainActivity.this,MY_CHANNEL)
                        .setContentTitle("Ban cap nhat moi")
                        .setContentText("Phien ban app 15.0")
                        .setShowWhen(true)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(
                                BitmapFactory.decodeResource(
                                        getResources(),
                                        R.drawable.icon_jetpack
                                ))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(
                                getResources(),
                                R.drawable.icon_jetpack
                        )));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel =
                            new NotificationChannel(
                                    MY_CHANNEL,
                                    "CHANNEL",
                                    NotificationManager.IMPORTANCE_HIGH);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.enableVibration(true);
                    mNotificationManager.createNotificationChannel(notificationChannel);
                }
                mNotificationManager.notify(1 , notification.build());
            }
        });
    }
}