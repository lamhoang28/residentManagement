package com.example.nhom6_datn.UI

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Patterns
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Animation.startAnimation
import com.example.nhom6_datn.MainActivity
import com.example.nhom6_datn.Model.Model_Member
import com.example.nhom6_datn.Model.User_Request
import com.example.nhom6_datn.R;
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityActiLoginBinding
import com.example.nhom6_datn.databinding.ActivityMainSplashScreenBinding
import com.example.nhom6_datn.util.shower
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import java.util.regex.Pattern

class Acti_Login : AppCompatActivity() {
    private lateinit var binding: ActivityActiLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_login)
        binding = ActivityActiLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ani =AnimationUtils.loadAnimation(this,R.anim.fade_zoom).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
        }

        checkGmail()
        checkPass()
        loadData()
        binding.ActiLoginAppCompatButton.setOnClickListener {
            var _gmail = binding.ActiLoginEmail.editText?.text.toString().trim()
            var _pass = binding.ActiLoginPassWord.editText?.text.toString().trim()
            var check=0

            if (_gmail.isEmpty()){
                binding.ActiLoginEmail.error = getString(R.string.ERROR)
            }else{
                check++
            }
            if (_pass.isEmpty()){
                binding.ActiLoginPassWord.error = getString(R.string.ERROR)
            }else{
                check++
            }
            if (check==2){
                checkLogin(_gmail,_pass)

//                Toast(this).shower("Update "+getString(R.string.successful),1, this as Activity)
//                binding.ActiLoginView.isInvisible = true
//                binding.ActiLoginView.startAnimation(ani){
//                    startActivity(Intent(this,MainActivity::class.java))
//                    finish()
////                Handler(Looper.getMainLooper()).postDelayed({
////
////                },100)
//                }
                check=0
            }


        }
    }

    private fun checkPass() {
        binding.ActiLoginPassWord.editText?.setOnFocusChangeListener { view, text ->
            if (!text){
                binding.ActiLoginPassWord.error = validate(binding.ActiLoginPassWord.editText!!.text.toString().trim())
            }
        }
    }

    private fun checkGmail(){
        binding.ActiLoginEmail.editText?.setOnFocusChangeListener { view, text ->
            if (!text){
                binding.ActiLoginEmail.error = validate(binding.ActiLoginEmail.editText!!.text.toString().trim())
            }
        }
    }

    private fun validate(text:String): String? {
        if(text.isEmpty()){
            return getString(R.string.ERROR)
        }
        return null
    }
    private fun checkLogin(userN:String,passW: String){
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val request = User_Request()
        request.userN = userN
        request.passW = passW
        val call =serviceGenator.login(request)
        call.enqueue(object : retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful&& response.body()!!.isNotEmpty()){
                    Log.e("TAG", "key: "+response.body() )
                    val callToken  =serviceGenator.checkToken(response.body().toString(),passW)
                    callToken.enqueue(object : retrofit2.Callback<Model_Member>{
                        override fun onResponse(
                            call: Call<Model_Member>,
                            response: Response<Model_Member>
                        ) {
                            if (response.isSuccessful){
                                Log.e("TAG", "checkToken: "+response.body() )
                                Toast(this@Acti_Login).shower("Update "+getString(R.string.success),1, this@Acti_Login)
                                val model: Model_Member? = response.body()

                                saveData(model!!,passW)
                                startActivity(Intent(this@Acti_Login,MainActivity::class.java))
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<Model_Member>, t: Throwable) {
                            Log.e("TAG", "onFailure: "+t.message )
                        }

                    })
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("TAG", "onFailure: "+t.message )
            }

        })
    }
//    private fun checkLogin(userN:String, passW:String){
//        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
//        var request = User_Request()
//        request.userN= userN
//        request.passW= passW
//        val call = serviceGenator.login(request)
//        call.enqueue(object :retrofit2.Callback<List<Model_Member>>{
//            override fun onResponse(
//                call: Call<List<Model_Member>>,
//                response: Response<List<Model_Member>>
//            ) {
//                if (response.isSuccessful){
//                    Toast(this@Acti_Login).shower("Update "+getString(R.string.success),1, this@Acti_Login)
//                    val model:Model_Member = response.body()!![0]
//
//                    saveData(model,passW)
//                    startActivity(Intent(this@Acti_Login,MainActivity::class.java))
//                    finish()
//                }
//            }
//
//            override fun onFailure(call: Call<List<Model_Member>>, t: Throwable) {
//                Toast(this@Acti_Login).shower(t.message.toString(),0, this@Acti_Login)
//                Log.e("TAG", "onResponse: "+t.message )
//            }
//
//        })
//    }
    @SuppressLint("CommitPrefEdits")
    private fun saveData(model:Model_Member,passW: String){
        val sharedPreferences = getSharedPreferences("member",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if(!binding.ActiLoginSwitch.isChecked){
            editor.clear()

        }else{
            editor.putString("gmail",model.getEmail())
            editor.putString("passW",passW)
            editor.putBoolean("key",binding.ActiLoginSwitch.isChecked)
        }
        val gson= Gson()
        val arr = gson.toJson(model)
        editor.putString("data",arr)
        editor.apply()
    }
    private fun loadData(){
        val sharedPreferences = getSharedPreferences("member",Context.MODE_PRIVATE)
        val saveUser:String? = sharedPreferences.getString("gmail",null)
        val savePssW:String? = sharedPreferences.getString("passW",null)
        val saveKey:Boolean = sharedPreferences.getBoolean("key",false)
        binding.ActiLoginEmail.editText?.setText(saveUser)
        binding.ActiLoginPassWord.editText?.setText(savePssW)
        binding.ActiLoginSwitch.isChecked= saveKey
    }
}