package com.example.recyclerview.contactAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ItemUserBinding
import com.example.recyclerview.model.User
import com.example.recyclerview.viewModel.userViewModel

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.UsersViewHolder>() {
    class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    private val users = ArrayList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            textViewName.text = user.name
            textViewCareer.text = user.career
            if (user.photo.isNotBlank()) {
                Glide.with(imageViewUserPhoto)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_photo)
                    .error(R.drawable.ic_user_photo)
                    .into(imageViewUserPhoto)
            } else {
                imageViewUserPhoto.setImageResource(R.drawable.ic_user_photo)
            }
        }

    }

    fun setUsers(userList: List<User>) {
        users.clear()
        users.addAll(userList)
    }
}