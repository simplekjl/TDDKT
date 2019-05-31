package com.simplekjl.tddkt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.Repository
import com.simplekjl.tddkt.data.models.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter(var posts: List<Post>, var listener : OnPostClicked, var repository: Repository) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.post_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.setItem(posts[position],listener)
    }


    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun setItem(
            post: Post,
            listener: OnPostClicked
        ) {
            view.post_title.text = post.title
            //get user data from the repository
            //view.post_username.text = repository.getUserById(post.userId)
            view.post_body.text = post.body
            Picasso.get()
                .load("https://api.adorable.io/avatars/285/oh.png")
                .into(view.post_profile_photo)
        }
    }

    interface OnPostClicked{
        fun onPostClicked()
    }
}