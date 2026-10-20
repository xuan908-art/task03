package com.example.task03

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Exp3MenuActivity : AppCompatActivity() {

    private lateinit var etTest: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp3)
        etTest = findViewById(R.id.et_test)
        title = "MenuTest"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // 使用XML定义的菜单
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_exp3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // 字体大小：小=10号，中=16号，大=20号
            R.id.font_small -> etTest.textSize = 10f
            R.id.font_medium -> etTest.textSize = 16f
            R.id.font_large -> etTest.textSize = 20f
            // 普通菜单项：弹出Toast
            R.id.menu_normal ->
                Toast.makeText(this, "你点击了普通菜单项", Toast.LENGTH_SHORT).show()
            // 字体颜色
            R.id.color_red -> etTest.setTextColor(Color.RED)
            R.id.color_black -> etTest.setTextColor(Color.BLACK)
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
