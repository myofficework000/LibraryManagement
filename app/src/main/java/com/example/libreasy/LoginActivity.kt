package com.example.libreasy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.libreasy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn){
            val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                putExtra("fragment", "profile")
            }
            startActivity(intent)
            finish()
        } else{
            userLogin()
        }

    }

    private fun userLogin(){
        with(binding){
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                if (username == "Yuan" && password == "123"){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java).apply{
                        putExtra("username", username)
                        putExtra("password", password)
                        putExtra("fragment", "profile")
                    }
                    isLoggedIn = true
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isLoggedIn", isLoggedIn)
                    editor.apply()
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}