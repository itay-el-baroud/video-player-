package com.cloudvideo.app.model
data class VideoModel(val id: String, val title: String, val video_url: String, val thumb_url: String, val type: String, val created_at: String, val views: Int, val qualities: Map<String, String>?)
