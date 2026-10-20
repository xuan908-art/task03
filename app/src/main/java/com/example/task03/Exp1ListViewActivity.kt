package com.example.task03

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.HashMap

class Exp1ListViewActivity : AppCompatActivity() {

    private val CHANNEL_ID = "animal_notify"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp1)

        // 显示ActionBar返回按钮（parentActivityName已配置，点击自动回主页）
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        createNotificationChannel()

        val listView: ListView = findViewById(R.id.listView)

        val dataList = ArrayList<HashMap<String, Any>>()

        var map = HashMap<String, Any>()
        map["name"] = "Lion"
        map["img"] = R.drawable.lion
        dataList.add(map)

        map = HashMap()
        map["name"] = "Tiger"
        map["img"] = R.drawable.tiger
        dataList.add(map)

        map = HashMap()
        map["name"] = "Monkey"
        map["img"] = R.drawable.monkey
        dataList.add(map)

        map = HashMap()
        map["name"] = "Dog"
        map["img"] = R.drawable.dog
        dataList.add(map)

        map = HashMap()
        map["name"] = "Cat"
        map["img"] = R.drawable.cat
        dataList.add(map)

        map = HashMap()
        map["name"] = "Elephant"
        map["img"] = R.drawable.elephant
        dataList.add(map)

        val adapter = SimpleAdapter(
            this,
            dataList,
            R.layout.list_item_animal,
            arrayOf("name", "img"),
            intArrayOf(R.id.tv_name, R.id.iv_pic)
        )
        listView.adapter = adapter

        // ==========【修改好的点击事件】==========
        listView.setOnItemClickListener { _, _, position, _ ->
            val item = dataList[position]
            val animalName = item["name"].toString()
            Toast.makeText(this, "选中：$animalName", Toast.LENGTH_SHORT).show()
            sendNotification(animalName)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "动物通知"
            val descriptionText = "点击列表项发出通知"
            val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(titleText: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(titleText)
            .setContentText("你点击选中了动物：$titleText")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1001, builder.build())
    }
}
