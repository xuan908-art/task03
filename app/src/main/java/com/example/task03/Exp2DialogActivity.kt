package com.example.task03

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Exp2DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.btn_show_dialog).setOnClickListener {
            showLoginDialog()
        }

        showLoginDialog()
    }

    private fun showLoginDialog() {
        // 加载自定义布局
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login, null)
        val etUsername = dialogView.findViewById<EditText>(R.id.et_username)
        val etPassword = dialogView.findViewById<EditText>(R.id.et_password)

        // 通过 setView() 将自定义布局添加到 AlertDialog
        AlertDialog.Builder(this)
            .setView(dialogView)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Sign in") { _, _ ->
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                Toast.makeText(
                    this,
                    "登录：$username / $password",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .show()
    }
}
