package com.example.nhom6_datn.Change_Language

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.Locale

class Language_Manager (var context: Context){

    fun  updateResource(string: String){
        val  locale =Locale(string)
        Locale.setDefault(locale)
        val resources =context.resources
        val configuration =resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration,resources.displayMetrics)
        setLang(string)
    }
    fun  setLang(string: String){
        val sharedPreferences = context.getSharedPreferences("LANG",Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("lang",string)
        editor.commit()

    }
    fun getLang():String{
        val sharedPreferences = context.getSharedPreferences("LANG",Context.MODE_PRIVATE)
        return sharedPreferences.getString("lang","en").toString()
    }
}