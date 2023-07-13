package com.example.nhom6_datn.Model

import java.util.Date

class Model_News :java.io.Serializable{
    private  var id_news:Int
    private  var title:String
    private  var message:String
    private  var dateNews:Date
    private  var link:String

    constructor(id_news: Int, title: String, message: String, dateNews: Date, link: String) {
        this.id_news = id_news
        this.title = title
        this.message = message
        this.dateNews = dateNews
        this.link = link
    }


    fun getId_news():Int{
        return id_news
    }
    fun getTitle():String{
        return title
    }
    fun getMessage():String{
        return message
    }
    fun getDate():Date{
        return dateNews
    }
    fun getLink():String{
        return link
    }

}