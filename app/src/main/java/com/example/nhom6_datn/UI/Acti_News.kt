package com.example.nhom6_datn.UI

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.MainActivity
import com.example.nhom6_datn.Model.Model_News
import com.example.nhom6_datn.News_Adapter
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityActiNewsBinding
import com.example.nhom6_datn.util.shower
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class Acti_News : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter:News_Adapter
    private lateinit var binding: ActivityActiNewsBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private var list= ArrayList<Model_News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_news)
        binding= ActivityActiNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //return main
        val returnMain = binding.ActiNewsReturn
        returnMain.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        //searhView
        val search = binding.ActiNewsSearchView
        search.clearFocus()
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return  false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
        shimmerFrameLayout = binding.ActiNewsShimmerFrameLayout
        recyclerView= binding.ActiNewsRecyclerView
        manager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)
        //f5
        swipeRefreshLayout = binding.ActiNewsSwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                CallAPI()
                swipeRefreshLayout.isRefreshing= false
              },3000)
        }
        list = ArrayList()
        CallAPI()
    }
    private fun CallAPI(){
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val call = serviceGenator.getPost()
        call.enqueue(object :retrofit2.Callback<MutableList<Model_News>>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<MutableList<Model_News>>,
                response: Response<MutableList<Model_News>>
            ) {
                if(response.isSuccessful&&response.body()!!.size>0){
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                    val data = response.body()!!
                    list.clear()
                    list.addAll(data)
                    adapter = News_Adapter(this@Acti_News,list)
                    recyclerView.adapter = adapter
                    recyclerView.hasFixedSize()
                    adapter.notifyDataSetChanged()

                }else{
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MutableList<Model_News>>, t: Throwable) {
                t.printStackTrace()
                Log.e("TAG", "onResponse: "+t.message.toString() )
            }

        })
    }
    private fun filterList(text:String?){
        var newList = java.util.ArrayList<Model_News>()
        if(text.toString().trim().isEmpty()){
            newList = list
        }else{
            for (i in list){
                if (i.getTitle().lowercase(Locale.ROOT).contains(text.toString())){
                    newList.add(i)
                }
            }
        }
        adapter.refeshData(newList)
    }
}