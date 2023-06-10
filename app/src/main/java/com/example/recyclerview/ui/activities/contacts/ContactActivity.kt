package com.example.recyclerview.ui.activities.contacts

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.ui.activities.contacts.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.databinding.ActivityContactsBinding
import com.example.recyclerview.domain.dataclass.User
import com.example.recyclerview.repository.IContactsRecyclerViewListener
import com.example.recyclerview.ui.fragments.contacts.dialogs.DialogFragment
import com.example.recyclerview.utils.Constants
import com.example.recyclerview.utils.ext.animateVisibility

class ContactActivity : AppCompatActivity() {
    private val binding: ActivityContactsBinding by lazy {
        ActivityContactsBinding.inflate(layoutInflater)
    }

    private val listener = object : IContactsRecyclerViewListener{
        override fun delete(user: User) {
            TODO("Delete ViewModel liveDataUser")
        }
    }

    private val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter(userViewModel, listener)
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
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContacts.layoutManager = layoutManager
        binding.recyclerViewContacts.adapter = adapter
        adapter.updateUsers(userViewModel.getUserList())


    }

    private fun setClickListener() {
        buttonAddContact()
    }

    // change method naming
    private fun buttonAddContact() {
        binding.textViewAddContacts.setOnClickListener {
            val dialogFragment = DialogFragment(userViewModel)
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
}