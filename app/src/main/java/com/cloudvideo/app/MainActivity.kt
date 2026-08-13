package com.cloudvideo.app
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudvideo.app.adapter.VideoAdapter
import com.cloudvideo.app.network.ApiService
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)
        scope.launch {
            try {
                val retrofit = Retrofit.Builder().baseUrl("https://YOUR_DOMAIN.com/").addConverterFactory(GsonConverterFactory.create()).build()
                val api = retrofit.create(ApiService::class.java)
                val videos = withContext(Dispatchers.IO) { api.getVideos() }
                recycler.adapter = VideoAdapter(videos) { video ->
                    val i = Intent(this@MainActivity, VideoPlayerActivity::class.java)
                    i.putExtra("url", video.video_url)
                    startActivity(i)
                }
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}
