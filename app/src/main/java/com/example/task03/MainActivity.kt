package com.example.task03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //实验1跳转
        findViewById<Button>(R.id.btn_exp1).setOnClickListener {
            val intent = Intent(this, Exp1ListViewActivity::class.java)
            startActivity(intent)
        }

        //实验2跳转
        findViewById<Button>(R.id.btn_exp2).setOnClickListener {
            val intent = Intent(this, Exp2DialogActivity::class.java)
            startActivity(intent)
        }

        //实验3跳转
        findViewById<Button>(R.id.btn_exp3).setOnClickListener {
            val intent = Intent(this, Exp3MenuActivity::class.java)
            startActivity(intent)
        }

        //实验4跳转
        findViewById<Button>(R.id.btn_exp4).setOnClickListener {
            val intent = Intent(this, Exp4ActionModeActivity::class.java)
            startActivity(intent)
        }

        //实验5跳转
        findViewById<Button>(R.id.btn_exp5).setOnClickListener {
            val intent = Intent(this, Exp5RecyclerActivity::class.java)
            startActivity(intent)
        }
    }
}
