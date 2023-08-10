package com.example.libreasy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.example.libreasy.R
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.dao.BookDao
import com.example.libreasy.databinding.FragmentLibraryBinding
import com.example.libreasy.view.adapter.BookListAdapter
import com.example.libreasy.view.adapter.GenrePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding
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
        binding = FragmentLibraryBinding.inflate(LayoutInflater.from(requireActivity()), container, false)

        val adapter = GenrePagerAdapter(childFragmentManager, lifecycle)
        binding.vpGenre.adapter = adapter
        TabLayoutMediator(binding.tabGenre, binding.vpGenre) {tab, position ->
            val genre = adapter.getGenre(position)
            tab.text = genre
        }.attach()

        val genres = bookDao.getAllGenres()
        adapter.setGenres(genres)

        return binding.root
    }


    private fun initDao() {
        databaseHelper = DatabaseHelper(requireContext())
        bookDao = BookDao(databaseHelper)
    }

    companion object {
        fun newInstance(): LibraryFragment {
            return LibraryFragment()
        }
    }

}