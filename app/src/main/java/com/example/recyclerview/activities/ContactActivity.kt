package com.example.recyclerview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.fragments.DialogFragment
import com.example.recyclerview.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.databinding.ActivityContactsBinding
import com.example.recyclerview.utils.Constants
import com.example.recyclerview.viewModel.UserViewModel

class ContactActivity : AppCompatActivity() {
    private val binding: ActivityContactsBinding by lazy {
        ActivityContactsBinding.inflate(layoutInflater)
    }
    private val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter(userViewModel)
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

    private fun View.animateVisibility(visibility: Int) {
        val animator: ViewPropertyAnimator = when (visibility) {
            View.VISIBLE -> animate().alpha(1f).setDuration(300)
            View.GONE -> animate().alpha(0f).setDuration(300)
            else -> return
        }
        animator.withStartAction {
            this.visibility = View.VISIBLE
        }.withEndAction {
            this.visibility = visibility
        }.start()
    }

}