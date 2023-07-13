package com.example.nhom6_datn.UI

import android.animation.LayoutTransition
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Change_Language.Language_Manager
import com.example.nhom6_datn.Model.Model_Member
import com.example.nhom6_datn.Model.User_Change_Pass
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.util.shower
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class Frag_Profile : Fragment() {



    private lateinit var us :TextView
    private lateinit var vn :TextView
    private lateinit var logout :TextView
    private lateinit var getSharedPreferences:SharedPreferences
    private lateinit var _member:String
    private lateinit var _model:Model_Member

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_frag__profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSharedPreferences = this.activity!!.getSharedPreferences("member",
            Context.MODE_PRIVATE)
        _member = getSharedPreferences.getString("data",null)!!
        val gson = Gson()
        _model = gson.fromJson(_member, Model_Member::class.java)

        val userN = view.findViewById<TextView>(R.id.Frag_Home_nameProFile)
        val roomN = view.findViewById<TextView>(R.id.Frag_Home_roomProFile)
        userN.text = _model.getNameCdan()
        roomN.text = _model.getId_Apartment()

        val info = view.findViewById<TextView>(R.id.Frag_Profile_info)
        info.setOnClickListener {
            startActivity(Intent(context, Acti_ViewInfo::class.java))
        }
        //RESET PASSWORD
        val changePass = view.findViewById<TextView>(R.id.Frag_Profile_change_password)
        changePass.setOnClickListener {
            val dialog = Dialog(view.context)
            dialog.setContentView(R.layout.dialog_confirm_change_password)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            val passWordOld = dialog.findViewById<TextInputLayout>(R.id.dialog_TextInputLayout_passWordOld)
            val passWordNew = dialog.findViewById<TextInputLayout>(R.id.dialog_TextInputLayout_passWordNew)
            val passWordNewR = dialog.findViewById<TextInputLayout>(R.id.dialog_TextInputLayout_passWordNewR)

            val yes= dialog.findViewById<AppCompatButton>(R.id.yes_change_dialog)
            val no= dialog.findViewById<AppCompatButton>(R.id.no_change_dialog)
            checkPass(passWordOld)
            checkPass(passWordNew)
            checkPass(passWordNewR)



            yes.setOnClickListener {

                val passOld:String = passWordOld.editText?.text.toString().trim()
                val passNew:String = passWordNew.editText?.text.toString().trim()
                val passNewR:String = passWordNewR.editText?.text.toString().trim()

                if (passOld.isEmpty()||passNew.isEmpty()||passNewR.isEmpty()){
                    Log.e("TAG", "khong de trong: ")
                    return@setOnClickListener
                }else if (passNewR != passNew){
                    // Toast(context).shower(getString(R.string.ERROR),0,context as Activity)
                    return@setOnClickListener
                }else{
                    updateMember(_model,passNew,dialog)
                }
            }
            no.setOnClickListener {
                dialog.dismiss()
            }
            val sharedPreferences = this.activity!!.getSharedPreferences("member",Context.MODE_PRIVATE)
            Log.e("TAG", "passs: "+sharedPreferences.getString("gmail",null) )
            dialog.show()

        }
        //CHANGE LANGUASE
        val linearLayout = view.findViewById<LinearLayout>(R.id.Frag_Profile_layout)
        val cardview = view.findViewById<CardView>(R.id.Frag_Profile_Cardview)
        val langguage = view.findViewById<TextView>(R.id.Frag_Profile_language)
        vn = view.findViewById(R.id.Frag_Profile_vn)
        us = view.findViewById(R.id.Frag_Profile_us)

        linearLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        cardview.setOnClickListener {
            val v = if (vn.visibility==View.GONE || us.visibility == View.GONE) View.VISIBLE else View.GONE
            vn.visibility = v
            us.visibility = v
            
        }
        us.setOnClickListener {onclick(us)}
        vn.setOnClickListener {onclick(vn)}
        //LOGOUT
        logout = view.findViewById(R.id.Frag_Profile_logout)
        logout.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_confirm)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val yes= dialog.findViewById<AppCompatButton>(R.id.yes_dialog)
            val no= dialog.findViewById<AppCompatButton>(R.id.no_dialog)
            no.setOnClickListener {
                Toast(context).shower(getString(R.string.cance),0,context as Activity)
                dialog.dismiss()
            }
            yes.setOnClickListener {
                Toast(context).shower(getString(R.string.success),1,context as Activity)
                dialog.dismiss()
                val sharedPreferences = this.activity!!.getSharedPreferences("member",Context.MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()
                startActivity(Intent(context,Acti_Login::class.java))
                activity?.finish()
            }
            dialog.show()
        }
    }
    private fun onclick(view: View){
        when(view){
            us->{
                Language_Manager(view.context).updateResource("en")
                Toast(context).shower("English",1,context as Activity)
            }
            vn->{
                Language_Manager(view.context).updateResource("vi")
                Toast(context).shower("Vietnamese",1,context as Activity)
            }
        }
        startActivity(Intent(context, Main_Splash_Screen::class.java))
        activity?.finish()
    }

    private fun validate(text:String): String? {
        if(text.isEmpty()){
            return getString(R.string.ERROR)
        }
        return null
    }
    private fun checkPass(textInputLayout: TextInputLayout) {
        textInputLayout.editText?.setOnFocusChangeListener { view, text ->
            if (!text){
                textInputLayout.error = validate(textInputLayout.editText!!.text.toString().trim())
            }
        }
    }

    private fun updateMember(modelMember: Model_Member,passWord:String, dialog: Dialog){
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val edit = User_Change_Pass()
        edit.id_resident =modelMember.getId().toString()
        edit.passWord = passWord
        edit.gmail =modelMember.getEmail()
        val call = serviceGenator.changePassWord(modelMember.toString(),edit)
        call.enqueue(object :retrofit2.Callback<User_Change_Pass>{
            override fun onResponse(
                call: Call<User_Change_Pass>,
                response: Response<User_Change_Pass>
            ) {
                Log.e("TAG", "onResponse: "+response.body() )
                dialog.dismiss()
            }

            override fun onFailure(call: Call<User_Change_Pass>, t: Throwable) {
                Log.e("TAG", "onFailure: "+t.message )
            }


        })
    }
}