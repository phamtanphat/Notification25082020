package com.example.notification25082020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mBtnNotification;
    String MY_CHANNEL = "MY_CHANNEL";
    NotificationManager mNotificationManager;
    int REQUEST_CODE_OPEN_ACTIVITY = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null) {
            String message = intent.getStringExtra("message");
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }

        mBtnNotification = findViewById(R.id.buttonNotification);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mBtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("message", "Hello Main");
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                REQUEST_CODE_OPEN_ACTIVITY,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notification =
                        new NotificationCompat.Builder(MainActivity.this, MY_CHANNEL)
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
                                )))
                                .setOngoing(true)
                                .addAction(android.R.drawable.star_on, "Open App", pendingIntent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
                mNotificationManager.notify(1, notification.build());
            }
        });
    }
}