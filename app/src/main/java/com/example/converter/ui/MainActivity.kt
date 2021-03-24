package com.example.converter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var ui :ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)
    }
}