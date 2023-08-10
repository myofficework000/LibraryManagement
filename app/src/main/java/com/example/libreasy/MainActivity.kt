package com.example.libreasy

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.databinding.ActivityLoginBinding
import com.example.libreasy.databinding.ActivityMainBinding
import com.example.libreasy.view.fragment.AuthorsFragment
import com.example.libreasy.view.fragment.HomeFragment
import com.example.libreasy.view.fragment.LibraryFragment
import com.example.libreasy.view.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataHelper: DatabaseHelper
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isRestart", true)
        editor.apply()
    }


    private fun initViews() {
        when (intent.getStringExtra("fragment")) {
            "home" -> handleMenuEvent(HomeFragment())
            "library" -> handleMenuEvent(LibraryFragment())
            "profile" -> handleMenuEvent(ProfileFragment())
            "authors"-> handleMenuEvent(AuthorsFragment())
        }
        binding.bvView.setOnItemSelectedListener { menuItems->
            when (menuItems.itemId){
                R.id.it_bottom_authors -> handleMenuEvent(AuthorsFragment())
                R.id.it_bottom_home -> handleMenuEvent(HomeFragment())
                R.id.it_bottom_library-> handleMenuEvent(LibraryFragment())
                R.id.it_bottom_profile-> handleMenuEvent(ProfileFragment())
            }
            true
        }

    }

    private fun initDatabase(){
        dataHelper = DatabaseHelper(this)
    }


    private fun handleMenuEvent(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fc_container, fragment).addToBackStack(null).commit()
    }
}