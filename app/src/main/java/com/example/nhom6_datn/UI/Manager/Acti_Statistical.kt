package com.example.nhom6_datn.UI.Manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nhom6_datn.MainActivity
import com.example.nhom6_datn.R
import com.example.nhom6_datn.databinding.ActivityActiStatisticalBinding

class Acti_Statistical : AppCompatActivity() {
    private lateinit var binding:ActivityActiStatisticalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_statistical)
        binding = ActivityActiStatisticalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ActiStatisticalReturn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}