package com.example.notificationsapp

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
//                createNotification(notification.toString())
                cn(notification.toString())
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
        val title="My Notification"

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            val importance:Int =NotificationManager.IMPORTANCE_DEFAULT
            val channel:NotificationChannel=NotificationChannel(channel_Id,title,importance).apply {
                description=text
            }
            val notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        else
        {
            val builder=NotificationCompat.Builder(this,channel_Id)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(this)){
                notify(notificationId,builder.build())
            }
        }
    }

    fun cn(text:String){
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