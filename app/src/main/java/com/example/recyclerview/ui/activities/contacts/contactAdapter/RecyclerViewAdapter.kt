package com.example.recyclerview.ui.activities.contacts.contactAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemUserBinding
import com.example.recyclerview.domain.dataclass.User
import com.example.recyclerview.repository.IContactsRecyclerViewListener
import com.example.recyclerview.utils.ext.loadImage
import com.example.recyclerview.ui.activities.contacts.UserViewModel
import com.google.android.material.snackbar.Snackbar


class RecyclerViewAdapter(
    private val userViewModel: UserViewModel,
    private val listener: IContactsRecyclerViewListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.UsersViewHolder>() {
    class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    //TODO https://kotlinlang.org/docs/coding-conventions.html#modifiers-order

    fun updateUsers(newUsers: ArrayList<User>) {
        users.clear()
        users.addAll(newUsers)
    }

    private val users = ArrayList<User>()



    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]

        holder.binding.imageViewDelete.setOnClickListener {
            listener.delete(user)


//            val positionUser = holder.adapterPosition // holder.bindingAdapterPosition
//            userViewModel.deleteUser(positionUser)
//            notifyItemRemoved(positionUser)
//            updateUsers(userViewModel.getUserList())
//            Snackbar.make(
//                it, "${user.name} has been removed",
////                it, getString(R.string.deletedContact, user.name),
//                Snackbar.LENGTH_LONG
//            )
//                .setAction("Restore") {
//                    userViewModel.addUser(user, positionUser)
//                    notifyItemInserted(positionUser)
//                    updateUsers(userViewModel.getUserList())
//                }.show()
        }

        with(holder.binding) {
            textViewName.text = user.name
            textViewCareer.text = user.career

            imageViewUserPhoto.loadImage(user.photo)
//            Glide.with(imageViewUserPhoto)
//                .load(user.photo)
//                .circleCrop()
//                .placeholder(R.drawable.ic_user_photo)
//                .error(R.drawable.ic_user_photo)
//                .into(imageViewUserPhoto)
        }
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }
}