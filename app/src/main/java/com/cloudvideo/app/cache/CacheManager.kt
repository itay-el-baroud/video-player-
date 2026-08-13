package com.cloudvideo.app.cache
import android.content.Context
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File
object CacheManager {
    private var simpleCache: SimpleCache? = null
    fun getCache(c: Context): SimpleCache {
        if (simpleCache == null) {
            val dir = File(c.cacheDir, "video_cache")
            simpleCache = SimpleCache(dir, LeastRecentlyUsedCacheEvictor(500L*1024L*1024L), StandaloneDatabaseProvider(c))
        }
        return simpleCache!!
    }
}
