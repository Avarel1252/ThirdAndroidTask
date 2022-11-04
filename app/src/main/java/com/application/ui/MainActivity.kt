package com.application.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.R
import com.application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}