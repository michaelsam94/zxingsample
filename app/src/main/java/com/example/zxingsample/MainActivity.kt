package com.example.zxingsample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var scanBtn: Button? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scanBtn = findViewById(R.id.scanBtn)
        scanBtn?.setOnClickListener {
            val i = Intent(this,SurfaceViewActivity::class.java)
            startActivity(i)
        }
    }



}