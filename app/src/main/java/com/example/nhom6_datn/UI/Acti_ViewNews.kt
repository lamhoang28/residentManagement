package com.example.nhom6_datn.UI

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Adapter.Comment_Adapter
import com.example.nhom6_datn.Model.Model_Comment
import com.example.nhom6_datn.Model.Model_Member
import com.example.nhom6_datn.Model.Model_News
import com.example.nhom6_datn.Model.User_Comment
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityActiViewNewsBinding
import com.example.nhom6_datn.util.shower
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Acti_ViewNews : AppCompatActivity() {

    private var list = kotlin.collections.ArrayList<Model_Comment>()
    private lateinit var recyclerView : RecyclerView
    private lateinit var manager : LinearLayoutManager
    private lateinit var adapter : Comment_Adapter
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout


    private lateinit var binding:ActivityActiViewNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_view_news)
        binding=ActivityActiViewNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageSlide = binding.ActiViewNewsImageSlider
        val intent = intent
        val bundle = intent.extras ?: return
        val model:Model_News = bundle.getSerializable("data") as Model_News
        val title = binding.ActiViewNewsTitle
        val message = binding.ActiViewNewsMessage
        val date = binding.ActiViewNewsDate
        title.text = model.getTitle()
        message.text = model.getMessage()
        date.text = model.getDate().toString()

        val arr = ArrayList<SlideModel>()
        val api = ServiceGenator()
        val arrImg = model.getLink().split(",")

        for (i in arrImg){
            val linkImg =api._api+i;
            arr.add(SlideModel(linkImg,ScaleTypes.CENTER_CROP))
        }
        imageSlide.setImageList(arr)

        var inputComment = binding.ActiViewNewsInputComment
        val buttonComment = binding.ActiViewNewsButtonComment
        buttonComment.setOnClickListener {
            var _inputComment = inputComment.editText?.text.toString().trim()
            if (_inputComment.isEmpty()){
                Toast(this).shower(getString(R.string.ERROR),0,this)
                return@setOnClickListener
            }else{
                createComment(model.getId_news().toString(),_inputComment)
                inputComment.editText?.text?.clear()
            }
        }
         recyclerView = binding.ActiViewNewsRecyclerView
         manager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = manager
        callApiComment(model.getId_news())
        shimmerFrameLayout = binding.ActiViewNewsShimmerFrameLayout

    }
    private fun createComment(id_post : String,message: String){
        val sharedPreferences = getSharedPreferences("member",Context.MODE_PRIVATE)
        val model = sharedPreferences?.getString("data",null)
        val gson = Gson()
        val _model = gson.fromJson(model, Model_Member::class.java)
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val values = User_Comment()
        values.id_resident =_model.getId().toString()
        values.id_post = id_post
        values.message =message
        val call = serviceGenator.comment(values)
        call.enqueue(object : retrofit2.Callback<kotlin.collections.List<Model_Comment>>{
            override fun onResponse(
                call: Call<List<Model_Comment>>,
                response: Response<List<Model_Comment>>
            ) {
                if (response.isSuccessful&& response.body()!!.isNotEmpty()){
                    Toast(this@Acti_ViewNews).shower(getString(R.string.success),1,this@Acti_ViewNews)
                    callApiComment(id_post.toInt())

                    Log.e("TAG", "ok: "+response.body() )
                }
            }

            override fun onFailure(call: Call<List<Model_Comment>>, t: Throwable) {
                Log.e("TAG", "onFailure: "+t.message )
                val dialog = Dialog(this@Acti_ViewNews)
                dialog.setContentView(R.layout.dialog_error)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val button = dialog.findViewById<Button>(R.id.yes_dialog)
                button.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }

        })
    }
    private fun callApiComment(index:Int){
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val call = serviceGenator.getApiComment(index)
        call.enqueue(object : retrofit2.Callback<kotlin.collections.List<Model_Comment>>{
            override fun onResponse(
                call: Call<List<Model_Comment>>,
                response: Response<List<Model_Comment>>
            ) {
                if (response.isSuccessful&&response.body()!!.isNotEmpty()){
                    if (list.isNotEmpty()){
                        list.clear()
                    }
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE

                    val data = response.body()!!
                    list.addAll(data)
                    adapter = Comment_Adapter(this@Acti_ViewNews,list)
                    recyclerView.adapter = adapter
                }else{
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Model_Comment>>, t: Throwable) {
            }

        })
    }

}