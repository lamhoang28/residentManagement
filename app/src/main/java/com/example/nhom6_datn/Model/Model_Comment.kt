package com.example.nhom6_datn.Model

import java.util.Date

class Model_Comment {
    private var fullName:String
    private var message:String
    private var dateComment:Date

    constructor(fulName: String, message: String, dateComment: Date) {
        this.fullName = fulName
        this.message = message
        this.dateComment = dateComment
    }

    fun getFullName():String{
        return fullName
    }

    fun getMessage():String{
        return message
    }
    fun getDate():Date{
        return dateComment
    }
}