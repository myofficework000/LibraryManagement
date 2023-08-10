package com.example.libreasy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreasy.R
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.dao.AuthorDao
import com.example.libreasy.database.dao.BookDao
import com.example.libreasy.databinding.FragmentAuthorsBinding
import com.example.libreasy.databinding.FragmentLibraryBinding
import com.example.libreasy.view.adapter.AuthorListAdapter
import com.example.libreasy.view.adapter.BookListAdapter


class AuthorsFragment : Fragment() {
    private lateinit var binding: FragmentAuthorsBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var authorDao: AuthorDao
    private lateinit var bookDao: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorsBinding.inflate(LayoutInflater.from(requireActivity()), container, false)

        val adapter = AuthorListAdapter(emptyList()) {author ->
            val dialog = AuthorDetailsFragment.newInstance(author.name)
            dialog.show(parentFragmentManager, "author_dialog")
        }
        binding.rvAuthors.adapter = adapter
        binding.rvAuthors.layoutManager = LinearLayoutManager(requireContext())
        val authors = authorDao.getAuthors()
        adapter.authors = authors
        adapter.notifyDataSetChanged()

        return binding.root
    }


    private fun initDao() {
        databaseHelper = DatabaseHelper(requireContext())
        authorDao = AuthorDao(databaseHelper)
        bookDao = BookDao(databaseHelper)
    }

}