package com.simplekjl.tddkt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.models.AlbumImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*

class AlbumImageAdapter(var images: List<AlbumImage>) :
    RecyclerView.Adapter<AlbumImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = images.size


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: AlbumImage) {
            Picasso.get()
                .load(image.thumbnailUrl)
                .into(view.imageView)
        }

    }
}