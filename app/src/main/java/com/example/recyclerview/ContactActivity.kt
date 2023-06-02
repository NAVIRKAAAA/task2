package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.Fragments.DialogFragment
import com.example.recyclerview.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.databinding.ActivityContactsBinding
import com.example.recyclerview.viewModel.UserViewModel

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private lateinit var adapter: RecyclerViewAdapter
    private var userViewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        adapter = RecyclerViewAdapter(userViewModel)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContacts.layoutManager = layoutManager
        binding.recyclerViewContacts.adapter = adapter
        adapter.updateUsers(userViewModel.getUserList())
        binding.textViewAddContacts.setOnClickListener{
            addContacts()
        }
    }
    private fun addContacts() {
        val dialogFragment = DialogFragment(userViewModel)
        dialogFragment.setAdapter(adapter)
        dialogFragment.show(supportFragmentManager, "add_contact_dialog")
    }
}