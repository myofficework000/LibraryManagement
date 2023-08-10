package com.example.libreasy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.libreasy.databinding.ActivityLoginBinding
import com.example.libreasy.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var isRestart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        isRestart = sharedPreferences.getBoolean("isRestart", false)

        if (isRestart){
            binding.viewClickable.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.putBoolean("isRestart", false)
                editor.apply()
                launchActivityLogin()
            }
        } else {
            launchActivityLogin()
        }


    }

    private fun launchActivityLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}