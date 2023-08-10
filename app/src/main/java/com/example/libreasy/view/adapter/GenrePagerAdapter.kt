package com.example.libreasy.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.libreasy.view.fragment.GenreFragment

class GenrePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var genres = listOf<String>()

    override fun getItemCount(): Int = genres.size

    override fun createFragment(position: Int): Fragment {
        val genre = genres[position]
        return GenreFragment.newInstance(genre)
    }

    fun setGenres(genres: List<String>) {
        this.genres = genres
        notifyDataSetChanged()
    }

    fun getGenre(position: Int): String {
        return genres[position]
    }
}