package com.example.aichatbot

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.aichatbot.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        isFirstTime()
    }

    private fun isFirstTime() {
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPreferenceManager = SharedPreferenceManager(this)
            if(sharedPreferenceManager.isFirstTime){
                startActivity(Intent(this,walkingThroughOnBoarding::class.java))
                finish()
            }
            else{
                startActivity(Intent(this,HomeScreen::class.java))
                finish()
            }
        },7000)
    }

}

