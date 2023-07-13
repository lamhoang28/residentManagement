package com.example.nhom6_datn.UI.Manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.nhom6_datn.Adapter.Manager_AdminSevice_Adapter
import com.example.nhom6_datn.Animation.ScreenSlidePagerActivity
import com.example.nhom6_datn.R
import com.example.nhom6_datn.databinding.FragmentFragManagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class Frag_Manager : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__manager, container, false)
    }
    //staff,resident,apartment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        call(view)
        val adapter = Manager_AdminSevice_Adapter(childFragmentManager,lifecycle)
        viewPager2.adapter =adapter
        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->tab.text = getString(R.string.home_Resident_Admin)
                1->tab.text = getString(R.string.home_staff_Admin)
                2->tab.text = getString(R.string.home_Apartment_Admin)
            }
        }.attach()
        viewPager2.setPageTransformer(ScreenSlidePagerActivity())
    }
    fun call(view: View){
        viewPager2 = view.findViewById(R.id.Frag_Manager_ViewPager2)
        tabLayout = view.findViewById(R.id.Frag_Manager_TabLayout)
    }
}