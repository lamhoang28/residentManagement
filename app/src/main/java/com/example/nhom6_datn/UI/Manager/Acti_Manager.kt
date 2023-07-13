package com.example.nhom6_datn.UI.Manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.nhom6_datn.Adapter.Manager_Adapter
import com.example.nhom6_datn.Animation.DepthPageTransformer
import com.example.nhom6_datn.Animation.ScreenSlidePagerActivity
import com.example.nhom6_datn.MainActivity
import com.example.nhom6_datn.R;
import com.example.nhom6_datn.databinding.ActivityActiManagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class Acti_Manager : AppCompatActivity() {
    private lateinit var blinding : ActivityActiManagerBinding
    private lateinit var returnActi : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_manager)
        blinding = ActivityActiManagerBinding.inflate(layoutInflater)
        setContentView(blinding.root)
        val adapter = Manager_Adapter(supportFragmentManager,lifecycle)

        blinding.ActiManagerViewPager2.adapter = adapter
        TabLayoutMediator(blinding.ActiManagerTabLayout,blinding.ActiManagerViewPager2){tab,position->
            when(position){
                0->{
                    tab.text=getString(R.string.home_Manage_Admin)
                }else->{
                    tab.text=getString(R.string.home_Statistical_Admin)

            }
            }
        }.attach()
        blinding.ActiManagerViewPager2.setPageTransformer(DepthPageTransformer())
        blinding.ActiManagerReturn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

}