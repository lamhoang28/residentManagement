package com.example.nhom6_datn.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nhom6_datn.UI.Manager.Frag_Apartment
import com.example.nhom6_datn.UI.Manager.Frag_Resident
import com.example.nhom6_datn.UI.Manager.Frag_Staff

class Manager_AdminSevice_Adapter(fragment: FragmentManager,lifecycle: Lifecycle):
    FragmentStateAdapter(fragment,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->Frag_Resident()
            1-> Frag_Staff()
            else->Frag_Apartment()
        }
    }
}