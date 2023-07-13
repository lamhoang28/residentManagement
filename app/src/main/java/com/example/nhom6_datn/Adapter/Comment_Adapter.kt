package com.example.nhom6_datn.Adapter

import android.content.Context
import android.util.JsonReader
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Model.Model_Comment
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.ViewHoder
import retrofit2.Call
import retrofit2.Response

class Comment_Adapter (val context: Context,val list:List<Model_Comment>):RecyclerView.Adapter<Comment_Adapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       if (list.isNotEmpty()){
           return list.size
       }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelComment = list[position]

        holder.BindView(modelComment)
    }


    class ViewHolder (view: View) :RecyclerView.ViewHolder(view){
        val member =  view.findViewById<TextView>(R.id.item_comment_name)
        val message = view.findViewById<TextView>(R.id.item_comment_message)
        val date = view.findViewById<TextView>(R.id.item_comment_date)
        fun BindView(modelComment: Model_Comment){
            member.text = modelComment.getFullName()
            message.text = modelComment.getMessage()
            date.text = modelComment.getDate().toString()
        }
    }
}