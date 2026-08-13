package com.cloudvideo.app
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
class MyFirebaseService : FirebaseMessagingService() {
    override fun onMessageReceived(msg: RemoteMessage) {
        val title = msg.notification?.title?: "video api"
        val body = msg.notification?.body?: "فيديو جديد"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "video_api_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(NotificationChannel(channelId, "Video API", NotificationManager.IMPORTANCE_HIGH))
        }
        val notification = NotificationCompat.Builder(this, channelId)
          .setSmallIcon(R.drawable.ic_notification)
          .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
          .setContentTitle(title).setContentText(body).setAutoCancel(true).build()
        manager.notify(1, notification)
    }
}
