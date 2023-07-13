package com.example.nhom6_datn.UI

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.nhom6_datn.Model.Model_Member
import com.example.nhom6_datn.R
import com.example.nhom6_datn.databinding.ActivityActiViewInfoBinding
import com.google.gson.Gson

class Acti_ViewInfo : AppCompatActivity() {
    private lateinit var name:TextView
    private lateinit var age:TextView
    private lateinit var address:TextView
    private lateinit var phone:TextView
    private lateinit var cccd:TextView
    private lateinit var binding:ActivityActiViewInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_view_info)
        binding = ActivityActiViewInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("member",Context.MODE_PRIVATE)
        val gson= Gson()
        val model = gson.fromJson(sharedPreferences.getString("data",null),Model_Member::class.java)
        binding.name.text = model.getNameCdan()
        binding.age.text = model.getAge()
        binding.address.text = model.getAddress()
        binding.phone.text = model.getPhone()
        binding.cccd.text =model.getCccd()
        binding.room.text = model.getId_Apartment()
        binding.career.text = model.getCarees()
    }
}