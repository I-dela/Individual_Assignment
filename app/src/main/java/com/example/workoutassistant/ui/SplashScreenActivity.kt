package com.example.workoutassistant.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.workoutassistant.R

class SplashScreenActivity : AppCompatActivity() {


    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        this.supportActionBar?.height

        Handler().postDelayed({

            startActivity(Intent(this,
                MainActivity::class.java))

            finish()
        }, SPLASH_TIME_OUT)
    }
}
