package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.databinding.ActivityContactsBinding
import com.example.recyclerview.viewModel.userViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RecyclerViewAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContacts.layoutManager = layoutManager
        binding.recyclerViewContacts.adapter = adapter
        val userViewModel = ViewModelProvider(this)[userViewModel::class.java]
        val userList = userViewModel.getUserList()
        adapter.setUsers(userList)

    }
}