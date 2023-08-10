package com.example.libreasy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.libreasy.R
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.dao.AuthorDao
import com.example.libreasy.database.entity.Author
import com.example.libreasy.databinding.FragmentAddAuthorBinding


class AddAuthorFragment : Fragment() {
    private lateinit var binding: FragmentAddAuthorBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var authorDao: AuthorDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAuthorBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnAddAuthor.setOnClickListener {
                val author = Author(name=etName.text.toString(), rating=etRating.text.toString().toInt(), birth = etBirth.text.toString())
                authorDao.addAuthor(author)
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                etName.text?.clear()
                etRating.text?.clear()
                etBirth.text?.clear()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
    private fun initDao() {
        databaseHelper = DatabaseHelper(requireContext())
        authorDao = AuthorDao(databaseHelper)
    }
}