package com.example.nhom6_datn.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

class User_Comment {
    @SerializedName("id_resident")
    @Expose
    lateinit var id_resident:String

    @SerializedName("id_news")
    @Expose
    lateinit var id_post:String

    @SerializedName("message")
    @Expose
    lateinit var message:String


}