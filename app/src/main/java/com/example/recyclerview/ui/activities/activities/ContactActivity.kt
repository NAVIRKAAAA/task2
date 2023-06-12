package com.example.recyclerview.ui.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.repository.UserItemClickListener
import com.example.recyclerview.ui.activities.fragments.DialogFragment
import com.example.recyclerview.ui.activities.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.databinding.ActivityContactsBinding
import com.example.recyclerview.domain.model.localdataset.model.User
import com.example.recyclerview.utils.Constants
import com.example.recyclerview.utils.ext.animateVisibility
import com.google.android.material.snackbar.Snackbar

class ContactActivity : AppCompatActivity(), UserItemClickListener {
    private val binding: ActivityContactsBinding by lazy {
        ActivityContactsBinding.inflate(layoutInflater)
    }
    private val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter()
    }
    private var userViewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialRecyclerview()
        setClickListener()
        setNavigationUpListeners()
    }

    private fun initialRecyclerview() {
        setTouchRecycleItemListener()
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val layoutManager = LinearLayoutManager(this)
        adapter.setUserItemClickListener(this)
        binding.recyclerViewContacts.layoutManager = layoutManager
        binding.recyclerViewContacts.adapter = adapter
        adapter.updateUsers(userViewModel.getUserList())
    }

    private fun setClickListener() {
        showAddContactsDialog()
    }

    private fun showAddContactsDialog() {
        binding.textViewAddContacts.setOnClickListener {
            val dialogFragment = DialogFragment()
            dialogFragment.setViewModel(userViewModel)
            dialogFragment.setAdapter(adapter)
            dialogFragment.show(supportFragmentManager, Constants.DIALOG_TAG)
        }
    }


    private fun setNavigationUpListeners() {
        binding.imageViewNavigationUp.viewTreeObserver.addOnScrollChangedListener {
            checkForDisplayUpNavigationButton()
        }
        binding.imageViewNavigationUp.setOnClickListener {
            binding.recyclerViewContacts.smoothScrollToPosition(0)
        }
    }

    private fun checkForDisplayUpNavigationButton() {
        val visibleItemCount = binding.recyclerViewContacts.childCount
        val layoutManager = binding.recyclerViewContacts.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        binding.imageViewNavigationUp.animateVisibility(
            if (lastVisibleItemPosition >= visibleItemCount) View.VISIBLE else View.GONE
        )
    }

    private fun setTouchRecycleItemListener() {
        val itemTouchCallback = setTouchCallBackListener()
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.recyclerViewContacts)
    }

    private fun setTouchCallBackListener(): ItemTouchHelper.Callback {
        return object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteUserWithRestore(
                    userViewModel.getUserList()[viewHolder.adapterPosition],
                    viewHolder.adapterPosition
                )
            }
        }
    }
    override fun onUserDelete(user: User, position: Int) {
        deleteUserWithRestore(user, position)
    }

    fun deleteUserWithRestore(user: User, position: Int) {
        if (userViewModel.deleteUser(user)) {
            adapter.notifyItemRemoved(position)
            adapter.updateUsers(userViewModel.getUserList())
            Snackbar.make(
                binding.recyclerViewContacts,
                getString(R.string.s_has_been_removed).format(user.name),
                Snackbar.LENGTH_LONG
            )
                .setAction(getString(R.string.restore)) {
                    if (userViewModel.addUser(user, position)) {
                        adapter.notifyItemInserted(position)
                        adapter.updateUsers(userViewModel.getUserList())
                    }
                }.show()
        }
    }
}