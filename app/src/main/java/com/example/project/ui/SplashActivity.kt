package com.example.project.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import java.util.Locale

@Suppress("DEPRECATION")
class SplashActivity:AppCompatActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resources.configuration.setLocale(Locale("ar"))
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        Handler(Looper.getMainLooper()).postDelayed({
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
            finishAffinity()
        }, 3000)
    }

}