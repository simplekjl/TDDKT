package com.simplekjl.tddkt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.models.Comment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentAdapter(var comments: List<Comment>, var listener: OnCommentClicked?) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(comments[position], listener)
    }


    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun setItem(
            comment: Comment,
            listener: OnCommentClicked?
        ) {
            view.comment_username.text = comment.email
            view.comment_body.text = comment.body
            Picasso.get()
                .load("https://api.adorable.io/avatars/285/meh.png")
                .into(view.comment_profile_photo)

        }
    }

    interface OnCommentClicked {
        fun onCommentClicked()
    }
}