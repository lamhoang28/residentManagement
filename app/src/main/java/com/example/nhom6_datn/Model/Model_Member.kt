package com.example.nhom6_datn.Model

class Model_Member :java.io.Serializable{
    private  var id_resident:Int
    private  var id_apartment:String
    private  var fullName:String
    private  var age:String
    private  var address:String
    private  var phone:String
    private  var sex:String
    private  var cccd:String
    private  var carrees:String
    private  var gmail:String
    private  var passWord:String

    constructor(
        id_resident: Int,
        id_apartment: String,
        fullName: String,
        age: String,
        address: String,
        phone: String,
        sex: String,
        cccd: String,
        carrees: String,
        gmail: String,
        passWord: String
    ) {
        this.id_resident = id_resident
        this.id_apartment = id_apartment
        this.fullName = fullName
        this.age = age
        this.address = address
        this.phone = phone
        this.sex = sex
        this.cccd = cccd
        this.carrees = carrees
        this.gmail = gmail
        this.passWord = passWord
    }


    fun getId():Int{
        return id_resident
    }
    fun getId_Apartment():String{
        return id_apartment
    }
    fun getNameCdan():String{
        return fullName
    }
    fun getPassW():String{
        return passWord
    }
    fun getEmail():String{
        return gmail
    }
    fun getAge():String{
        return age
    }
    fun getAddress():String{
        return address
    }
    fun getPhone():String{
        return phone
    }
    fun getCccd():String{
        return cccd
    }
    fun getCarees():String{
        return carrees
    }
}