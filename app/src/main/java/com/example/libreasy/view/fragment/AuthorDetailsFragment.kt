package com.example.libreasy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreasy.R
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.dao.AuthorDao
import com.example.libreasy.database.dao.BookDao
import com.example.libreasy.database.entity.Author
import com.example.libreasy.databinding.FragmentAuthorDetailsBinding
import com.example.libreasy.view.adapter.BookListAdapter


class AuthorDetailsFragment : DialogFragment() {
    private lateinit var binding: FragmentAuthorDetailsBinding
    private lateinit var bookDao: BookDao
    private lateinit var authorDao: AuthorDao
    private lateinit var author: Author
    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val authorName = it.getString(ARG_AUTHOR_NAME)!!
            initDao()
            author = authorDao.getAuthorByName(authorName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorDetailsBinding.inflate(inflater, container, false)

        binding.tvName.text = author.name
        binding.tvYear.text = author.birth
        binding.rbRating.rating = author.rating.toFloat()
        binding.tvGenres.text = "Genres: ${bookDao.getGenresByAuthor(author.name)}"

        bookListAdapter = BookListAdapter(emptyList()){book ->
            val dialog = BookDetailsFragment.newInstance(book.title)
            dialog.show(parentFragmentManager, "book_dialog")
        }
        binding.rvBooks.adapter = bookListAdapter
        binding.rvBooks.layoutManager = LinearLayoutManager(requireContext())
        val books = bookDao.getBooksByAuthor(author.name)
        bookListAdapter.books = books
        bookListAdapter.notifyDataSetChanged()

        return binding.root
    }


    private fun initDao() {
        val databaseHelper = DatabaseHelper(requireContext())
        bookDao = BookDao(databaseHelper)
        authorDao = AuthorDao(databaseHelper)
    }

    companion object {
        private const val ARG_AUTHOR_NAME = "author_name"

        fun newInstance(authorName: String): AuthorDetailsFragment {
            val args = Bundle().apply {
                putString(ARG_AUTHOR_NAME, authorName)
            }
            return AuthorDetailsFragment().apply {
                arguments = args
            }
        }
    }

}