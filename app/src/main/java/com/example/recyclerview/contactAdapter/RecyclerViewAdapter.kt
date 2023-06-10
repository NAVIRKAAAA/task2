package com.example.recyclerview.contactAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ItemUserBinding
import com.example.recyclerview.model.User
import com.example.recyclerview.viewModel.UserViewModel
import com.google.android.material.snackbar.Snackbar


class RecyclerViewAdapter(private val userViewModel: UserViewModel) :
    RecyclerView.Adapter<RecyclerViewAdapter.UsersViewHolder>() {
    class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    private val users = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]

        holder.binding.imageViewDelete.setOnClickListener {
            val positionUser = holder.adapterPosition
            userViewModel.deleteUser(positionUser)
            notifyItemRemoved(positionUser)
            updateUsers(userViewModel.getUserList())
            Snackbar.make(
                it, "${user.name} has been removed",
                Snackbar.LENGTH_LONG
            )
                .setAction("Restore") {
                    userViewModel.addUser(user, positionUser)
                    notifyItemInserted(positionUser)
                    updateUsers(userViewModel.getUserList())
                }.show()
        }

        with(holder.binding) {
            textViewName.text = user.name
            textViewCareer.text = user.career
            Glide.with(imageViewUserPhoto)
                .load(user.photo)
                .circleCrop()
                .placeholder(R.drawable.ic_user_photo)
                .error(R.drawable.ic_user_photo)
                .into(imageViewUserPhoto)
        }
    }

    override fun getItemCount(): Int = users.size

    fun updateUsers(newUsers: ArrayList<User>) {
        users.clear()
        users.addAll(newUsers)
    }
}