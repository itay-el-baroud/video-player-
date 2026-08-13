package com.cloudvideo.app.network
import com.cloudvideo.app.model.VideoModel
import retrofit2.http.GET
interface ApiService { @GET("api.php") suspend fun getVideos(): List<VideoModel> }
