package com.example.nhom6_datn.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.nhom6_datn.R;
@SuppressLint("MissingInflatedId")
fun Toast.shower(message: String, int: Int, activity: Activity)
{
    val layout = activity.layoutInflater.inflate (
        R.layout.custom_toast,
        activity.findViewById(R.id.cl_customToastContainer)
    )

    val textView = layout.findViewById<TextView>(R.id.tv_message)

    textView.text = message
    val relativeLayout = layout.findViewById<RelativeLayout>(R.id.custom_toast)
    val status = layout.findViewById<TextView>(R.id.img_icon)


    when(int){
        0 ->{
            relativeLayout.background = ContextCompat.getDrawable(activity,R.drawable.error_shape)
            status.background = ContextCompat.getDrawable(activity,R.drawable.error_shape)

        }
    }

    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}
