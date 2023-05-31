package com.example.recyclerview.Fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.recyclerview.R
import com.example.recyclerview.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.viewModel.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class DialogFragment : AppCompatDialogFragment() {
    private val adapter = RecyclerViewAdapter()
    private val userViewModel = UserViewModel()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.activity_add_user, null)
        builder.setView(dialogView)
            .setPositiveButton("Save") { dialog, which ->
                val name =
                    dialogView.findViewById<TextInputEditText>(R.id.textInputEditTextFullName).text.toString()
                val career =
                    dialogView.findViewById<TextInputEditText>(R.id.textInputEditTextCareer).text.toString()

            }
            .setNegativeButton("Cancel") { dialog, which ->
            }
        return builder.create()
    }
}