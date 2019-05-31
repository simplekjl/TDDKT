package com.simplekjl.tddkt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.Repository
import com.simplekjl.tddkt.data.models.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*
import kotlin.random.Random

class PostAdapter(
    var posts: List<Post>,
    var viewLifecycleOwner: LifecycleOwner,
    var listener: OnPostClicked
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var repository: Repository

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        repository = Repository()
        val view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(posts[position])
    }


    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var repository: Repository = Repository()
        private val nextValues = List(10) { Random.nextInt(0, 100) }
        fun setItem(
            post: Post
        ) {
            view.post_title.text = post.title
            //get user data from the repository
            repository.getUserById(post.userId).observe(viewLifecycleOwner, Observer {
                view.post_username.text = it.name
            })
            repository.getCommentsCountByPostId(post.id).observe(viewLifecycleOwner, Observer {
                view.post_comments_count.text = it.toString()
            })
            view.post_body.text = post.body
            Picasso.get()
                .load("https://api.adorable.io/avatars/285/${nextValues[2]}.png")
                .into(view.post_profile_photo)
            view.post_card.setOnClickListener {
                listener.onPostClicked(post.id)
            }
        }
    }

    interface OnPostClicked {
        fun onPostClicked(postId: Int)
    }
}