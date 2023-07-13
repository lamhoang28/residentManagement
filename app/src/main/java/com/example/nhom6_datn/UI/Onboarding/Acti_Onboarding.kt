package com.example.nhom6_datn.UI.Onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.nhom6_datn.Adapter.Onboarding_Adapter
import com.example.nhom6_datn.Animation.DepthPageTransformer
import com.example.nhom6_datn.R
import com.example.nhom6_datn.databinding.ActivityActiOnboardingBinding

class Acti_Onboarding : AppCompatActivity() {
    private lateinit var binding: ActivityActiOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_onboarding)
        binding = ActivityActiOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter= Onboarding_Adapter(supportFragmentManager,lifecycle)

        binding.ActiOnboardingViewPager2.adapter = adapter
        binding.ActiOnboardingCircleIndicator3.setViewPager(binding.ActiOnboardingViewPager2)
        binding.ActiOnboardingViewPager2.setPageTransformer(DepthPageTransformer())
    }
}