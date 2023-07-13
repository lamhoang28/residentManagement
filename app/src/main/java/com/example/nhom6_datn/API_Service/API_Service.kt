package com.example.nhom6_datn.API_Service

import com.example.nhom6_datn.Model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface API_Service {
    @GET("/news/newsApi")
    fun getPost():Call<MutableList<Model_News>>

    @POST("/newsComment")
    fun comment(
        @Body comment: User_Comment
    ):Call<List<Model_Comment>>

    @GET ("/newsCommentApi/{id}")
    fun getApiComment(
        @Path("id") _id:Int
    ):Call<List<Model_Comment>>


//    @POST("/login")
//    fun login(
//        @Body request: User_Request
//    ):Call<List<Model_Member>>
    @POST("/login")
    fun login(
        @Body request: User_Request
    ):Call<String>

    @GET("/checkToken")
    fun checkToken(
        @Query("tokenn") tokenn:String,@Query("passW") passW:String
    ):Call<Model_Member>

    @PUT("/users/changePassWord/{id}")
    fun changePassWord(
        @Path("id") _id:String ,@Body change:User_Change_Pass
    ):Call<User_Change_Pass>
}