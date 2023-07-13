package com.example.nhom6_datn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nhom6_datn.Adapter.Home_Adapter
import com.example.nhom6_datn.Animation.DepthPageTransformer
import com.example.nhom6_datn.Animation.ScreenSlidePagerActivity
import com.example.nhom6_datn.Change_Language.Language_Manager
import com.example.nhom6_datn.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = Home_Adapter(supportFragmentManager,lifecycle)
        binding.MainActivityViewPager2.adapter=adapter
        TabLayoutMediator(binding.MainActivityTabLayout,binding.MainActivityViewPager2){tab,position->
            when(position){
                0->{
                    tab.setIcon(R.drawable.ic_home)
                    tab.orCreateBadge.number =999
                }
                1->{
                    tab.setIcon(R.drawable.ic_notifications)
                }
                else->{
                    tab.setIcon(R.drawable.ic_profile)
                }
            }
        }.attach()
        binding.MainActivityViewPager2.setPageTransformer(DepthPageTransformer())
    }

}