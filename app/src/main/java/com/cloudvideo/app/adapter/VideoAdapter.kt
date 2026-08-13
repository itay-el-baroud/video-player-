package com.cloudvideo.app.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cloudvideo.app.R
import com.cloudvideo.app.model.VideoModel
class VideoAdapter(private val list: List<VideoModel>, private val onClick: (VideoModel)->Unit): RecyclerView.Adapter<VideoAdapter.VH>() {
    class VH(v: View): RecyclerView.ViewHolder(v) {
        val typeBadge: TextView = v.findViewById(R.id.typeBadge)
        val viewsText: TextView = v.findViewById(R.id.viewsText)
        val timeText: TextView = v.findViewById(R.id.timeText)
        val thumb: ImageView = v.findViewById(R.id.thumbImage)
        val title: TextView = v.findViewById(R.id.titleText)
    }
    override fun onCreateViewHolder(p: ViewGroup, t: Int): VH = VH(LayoutInflater.from(p.context).inflate(R.layout.item_video, p, false))
    override fun getItemCount() = list.size
    override fun onBindViewHolder(h: VH, pos: Int) {
        val item = list[pos]
        h.title.text = item.title
        h.viewsText.text = "عدد المشاهدات ${item.views}"
        h.timeText.text = "تم ارسال هذا البث في توقيت ${item.created_at}"
        if (item.type == "live") {
            h.typeBadge.text = "live feed / بث مباشر"
            h.typeBadge.setBackgroundColor(Color.parseColor("#FF0000"))
        } else {
            h.typeBadge.text = "Recorded broadcast / بث مسجل"
            h.typeBadge.setBackgroundColor(Color.parseColor("#808080"))
        }
        Glide.with(h.itemView).load(item.thumb_url).into(h.thumb)
        h.itemView.setOnClickListener { onClick(item) }
    }
}
