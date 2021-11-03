package com.example.notificationsapp

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val channel_Id="channel_id_654"
    val notificationId=123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {
            val notification=binding.edNotification.text
            if(notification.toString().isNotEmpty())
            {
                createNotification(notification.toString())
                notification.clear()
            }
            else
            {
                Toast.makeText(applicationContext, "filed is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
    @SuppressLint("ObsoleteSdkInt")

    fun createNotification(text:String){
        var builder:Notification.Builder
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(channel_Id,text,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channel_Id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My Notification")
                .setContentText(text)
        }
        else
        {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My Notification")
                .setContentText((text))
        }

        notificationManager.notify(notificationId, builder.build())
    }

}
