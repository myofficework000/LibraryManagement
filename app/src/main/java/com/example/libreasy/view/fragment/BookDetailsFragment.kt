package com.example.libreasy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.libreasy.R
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.dao.AuthorDao
import com.example.libreasy.database.dao.BookDao
import com.example.libreasy.database.entity.Book
import com.example.libreasy.databinding.FragmentAuthorDetailsBinding
import com.example.libreasy.databinding.FragmentBookDetailsBinding
import com.example.libreasy.view.adapter.BookListAdapter


class BookDetailsFragment : DialogFragment() {
    private lateinit var binding: FragmentBookDetailsBinding
    private lateinit var bookDao: BookDao
    private lateinit var authorDao: AuthorDao
    private lateinit var book: Book


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val bookTitle = it.getString(BookDetailsFragment.ARG_BOOK_TITLE)!!
            initDao()
            book = bookDao.getBookByTitle(bookTitle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false)

        binding.tvTitle.text = book.title
        binding.tvAuthor.text = book.author
        binding.tvGenre.text = "Genre: ${book.genre}"
        binding.rbRating.rating = book.rating.toFloat()
        binding.tvYear.text = "Published Year: ${book.year.toString()}"
        binding.tvDescription.text = "Description: ${book.description.toString()}"

        return binding.root
    }

    private fun initDao() {
        val databaseHelper = DatabaseHelper(requireContext())
        bookDao = BookDao(databaseHelper)
        authorDao = AuthorDao(databaseHelper)
    }

    companion object {
        private const val ARG_BOOK_TITLE = "book_title"

        fun newInstance(bookTitle: String): BookDetailsFragment {
            val args = Bundle().apply {
                putString(ARG_BOOK_TITLE, bookTitle)
            }
            return BookDetailsFragment().apply {
                arguments = args
            }
        }
    }
}