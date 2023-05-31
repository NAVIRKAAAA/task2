package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.Fragments.DialogFragment
import com.example.recyclerview.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.databinding.ActivityContactsBinding
import com.example.recyclerview.viewModel.UserViewModel

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val userViewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RecyclerViewAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContacts.layoutManager = layoutManager
        binding.recyclerViewContacts.adapter = adapter
        val userViewModel = ViewModelProvider(this)[userViewModel::class.java]
        adapter.updateUsers(userViewModel.getUserList())
        val addButton = findViewById<TextView>(R.id.textViewAddContacts)
        addButton.setOnClickListener {
            val dialogFragment = DialogFragment()
            dialogFragment.show(supportFragmentManager, "add_contact_dialog")
        }
    }

}