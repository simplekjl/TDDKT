package com.simplekjl.tddkt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.models.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(var users: List<User>, var listener : OnUserClicked) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(users[position],listener)
    }


    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {



        fun setItem(user: User, listener: OnUserClicked) {
            Picasso.get()
                .load("https://api.adorable.io/avatars/285/oh.png")
                .into(view.user_profile_photo)

            view.comment_username.text = user.name
            view.comment_user_email.text = user.email
            view.comment_body.text = context.getString(R.string.at).plus(user.username)
            view.more_details.setOnClickListener { listener.onUserClicked(user.id) }
        }
    }

    interface OnUserClicked {
        fun onUserClicked(position: Int)
    }

}