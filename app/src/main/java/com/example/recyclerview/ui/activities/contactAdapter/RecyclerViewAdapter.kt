package com.example.recyclerview.ui.activities.contactAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.repository.UserItemClickListener
import com.example.recyclerview.databinding.ItemUserBinding
import com.example.recyclerview.domain.model.localdataset.model.User
import com.example.recyclerview.ui.activities.activities.UserViewModel
import com.example.recyclerview.utils.ext.loadImage


class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.UsersViewHolder>() {

    private var listener: UserItemClickListener? = null

    class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    private val users = ArrayList<User>()

    fun setUserItemClickListener(listener: UserItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]

        holder.binding.imageViewDelete.setOnClickListener {
            val positionUser = holder.bindingAdapterPosition
            listener?.onUserDelete(user, positionUser)
        }

        with(holder.binding) {
            textViewName.text = user.name
            textViewCareer.text = user.career
            imageViewUserPhoto.loadImage(user.photo)
        }
    }

    override fun getItemCount(): Int = users.size

    fun updateUsers(newUsers: ArrayList<User>) {
        users.clear()
        users.addAll(newUsers)
    }
}