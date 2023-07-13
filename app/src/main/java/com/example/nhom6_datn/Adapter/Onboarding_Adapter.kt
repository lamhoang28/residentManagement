package com.example.nhom6_datn.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nhom6_datn.UI.Frag_Home
import com.example.nhom6_datn.UI.Frag_Notification
import com.example.nhom6_datn.UI.Frag_Profile
import com.example.nhom6_datn.UI.Onboarding.Frag_Tab1
import com.example.nhom6_datn.UI.Onboarding.Frag_Tab2
import com.example.nhom6_datn.UI.Onboarding.Frag_Tab3

class Onboarding_Adapter(fragmentManager: FragmentManager,lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                Frag_Tab1()
            }
            1->{
                Frag_Tab2()
            }
            else ->{
                Frag_Tab3()
            }
        }
    }
}