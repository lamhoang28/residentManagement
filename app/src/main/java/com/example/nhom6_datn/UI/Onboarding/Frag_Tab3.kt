package com.example.nhom6_datn.UI.Onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.nhom6_datn.R
import com.example.nhom6_datn.UI.Acti_Login
import com.example.nhom6_datn.util.shower


class Frag_Tab3 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<ImageView>(R.id.Frag_Tab3_img)
        val welcome = view.findViewById<AppCompatButton>(R.id.Frag_Tab3_AppCompatButton)
        val animation  = AnimationUtils.loadAnimation(context,R.anim.fade_in)
        textView.startAnimation(animation)

        val animation_Button = AnimationUtils.loadAnimation(context,R.anim.fade_up)
        welcome.startAnimation(animation_Button)
        welcome.setOnClickListener {
            Toast(context).shower(getString(R.string.Wellcome),1,context as Activity)
            startActivity(Intent(context,Acti_Login::class.java))
            activity?.finish()
        }
    }
}