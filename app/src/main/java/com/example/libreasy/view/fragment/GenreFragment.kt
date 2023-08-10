package com.example.libreasy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreasy.R
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.dao.BookDao
import com.example.libreasy.databinding.FragmentGenreBinding
import com.example.libreasy.databinding.FragmentLibraryBinding
import com.example.libreasy.view.adapter.BookListAdapter

class GenreFragment : Fragment() {
    private lateinit var binding: FragmentGenreBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var bookDao: BookDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreBinding.inflate(LayoutInflater.from(requireActivity()), container, false)

        val adapter = BookListAdapter(emptyList()){book ->
            val dialog = BookDetailsFragment.newInstance(book.title)
            dialog.show(parentFragmentManager, "book_dialog")
        }
        binding.rvBooks.adapter = adapter
        binding.rvBooks.layoutManager = LinearLayoutManager(requireContext())
        val genre = requireArguments().getString(ARG_GENRE)?:""
        val books = bookDao.getBooksByGenre(genre)
        adapter.books = books
        adapter.notifyDataSetChanged()

        return binding.root
    }

    private fun initDao() {
        databaseHelper = DatabaseHelper(requireContext())
        bookDao = BookDao(databaseHelper)
    }

    companion object {
        private const val ARG_GENRE = "genre"

        fun newInstance(genre: String): GenreFragment {
            val fragment = GenreFragment()
            val args = Bundle()
            args.putString(ARG_GENRE, genre)
            fragment.arguments = args
            return fragment
        }
    }
}