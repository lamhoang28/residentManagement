package com.example.nhom6_datn.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nhom6_datn.UI.Manager.Frag_Apartment
import com.example.nhom6_datn.UI.Manager.Frag_Manager

class Manager_Adapter(fragmentManager: FragmentManager,lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
            return when(position){
                0->{
                    Frag_Manager()
                }else ->{
                    Frag_Apartment()
                }

        }
    }
}