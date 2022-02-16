package com.example.usershilt.features.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.usershilt.R
import dagger.hilt.android.AndroidEntryPoint


const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}