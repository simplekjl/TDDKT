package com.simplekjl.tddkt.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.databinding.UserItemBinding
import com.squareup.picasso.Picasso
import org.jetbrains.annotations.NotNull

class UserAdapter(var users: List<User>, private val listener: OnUserClicked?) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    companion object {

        @JvmStatic
        @BindingAdapter(value = ["bind:imageUrl", "bind:errorImage"], requireAll = true)
        fun loadImage(@NotNull view: ImageView, @NotNull url: String, urlErrorImage: Drawable) {
            //"https://api.adorable.io/avatars/285/oh.png"
            Picasso.get()
                .load(url)
                .error(urlErrorImage)
                .into(view)
        }
    }

    private lateinit var binding: UserItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //getting the layout inflater
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.user_item, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.setItem(user, listener)
    }


    inner class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(user: User, listener: OnUserClicked?) {
            // setting the variables in the view
            binding.user = user
            binding.moreDetails.setOnClickListener { listener?.onUserClicked(user.id) }
            binding.executePendingBindings()
        }
    }

    interface OnUserClicked {
        fun onUserClicked(position: Int)
    }
}