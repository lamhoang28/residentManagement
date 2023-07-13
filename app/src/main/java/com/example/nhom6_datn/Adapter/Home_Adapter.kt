package com.example.nhom6_datn.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nhom6_datn.UI.Frag_Home
import com.example.nhom6_datn.UI.Frag_Notification
import com.example.nhom6_datn.UI.Frag_Profile

class Home_Adapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    :FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
            0->{
                Frag_Home()
            }
            1->{
                Frag_Notification()
            }
            else ->{
                Frag_Profile()
            }
        }
    }
}