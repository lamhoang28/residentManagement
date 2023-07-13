package com.example.nhom6_datn.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User_Request {
    @SerializedName("gmail")
    @Expose
    lateinit var userN:String

    @SerializedName("passWord")
    @Expose
    lateinit var passW:String

}