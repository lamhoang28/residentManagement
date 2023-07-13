package com.example.nhom6_datn.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User_Change_Pass {

    @SerializedName("gmail")
    @Expose
    lateinit var gmail:String

    @SerializedName("passWord")
    @Expose
    lateinit var passWord:String

    @SerializedName("id")
    @Expose
    lateinit var id_resident:String
}