package com.example.libreasy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreasy.R
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.dao.AuthorDao
import com.example.libreasy.database.dao.BookDao
import com.example.libreasy.databinding.FragmentHomeBinding
import com.example.libreasy.view.adapter.BookListAdapter
import com.google.android.material.search.SearchBar


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var bookDao: BookDao
    private lateinit var authorDao: AuthorDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDao()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(requireActivity()), container, false)

        val bookNum = bookDao.getBooks().size
        val authorNum = authorDao.getAuthors().size
        val books = bookDao.getBooksSortedByYear()
        binding.tvStats.text = "$bookNum Books & $authorNum Authors in Collection"

        val adapter = BookListAdapter(emptyList()){book ->
            val dialog = BookDetailsFragment.newInstance(book.title)
            dialog.show(parentFragmentManager, "book_dialog")
        }
        binding.rvBooks.adapter = adapter
        binding.rvBooks.layoutManager = LinearLayoutManager(requireContext())
        adapter.books = books
        adapter.notifyDataSetChanged()

        binding.searchBar.setOnQueryTextListener(object:android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredBooks = books.filter { book -> book.title.contains(newText.toString(), ignoreCase = true) }
                adapter.books = filteredBooks
                adapter.notifyDataSetChanged()
                return true
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnAddBooks.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fc_container, AddBookFragment())?.addToBackStack(null)?.commit()
            }
            btnAddAuthors.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fc_container, AddAuthorFragment())?.addToBackStack(null)?.commit()
            }
        }
    }

    private fun initDao() {
        databaseHelper = DatabaseHelper(requireContext())
        bookDao = BookDao(databaseHelper)
        authorDao = AuthorDao(databaseHelper)
    }


}