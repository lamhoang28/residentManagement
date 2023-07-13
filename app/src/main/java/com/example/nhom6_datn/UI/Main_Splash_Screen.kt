package com.example.nhom6_datn.UI

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.nhom6_datn.Change_Language.Language_Manager
import com.example.nhom6_datn.MainActivity
import com.example.nhom6_datn.R
import com.example.nhom6_datn.UI.Onboarding.Acti_Onboarding
import com.example.nhom6_datn.databinding.ActivityActiOnboardingBinding
import com.example.nhom6_datn.databinding.ActivityMainSplashScreenBinding

class Main_Splash_Screen : AppCompatActivity() {
    private lateinit var binding: ActivityMainSplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash_screen)
        binding = ActivityMainSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.MainSplashScreenImg.alpha = 0f
        binding.MainSplashScreenImg.animate().setDuration(2600).alpha(1f).withEndAction {
            val sharedPreferences = getSharedPreferences("member",Context.MODE_PRIVATE)
            val key = sharedPreferences.getBoolean("key",false)
            var i =1
            if (key){
                startActivity(Intent(this,MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            }else{
                startActivity(Intent(this,Acti_Onboarding::class.java))
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            }
        }
        val languageManager = Language_Manager(this)
        languageManager.updateResource(languageManager.getLang())
    }
}