package com.example.libreasy.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.libreasy.database.entity.Author
import com.example.libreasy.databinding.ItemAuthorBinding

class AuthorListAdapter(var authors:List<Author>, private val listener: (author: Author) -> Unit) : RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>(){
    private lateinit var binding: ItemAuthorBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AuthorListAdapter.AuthorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemAuthorBinding.inflate(layoutInflater, parent, false)
        return AuthorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthorListAdapter.AuthorViewHolder, position: Int) {
        holder.bind(authors[position])
    }

    override fun getItemCount() = authors.size

    inner class AuthorViewHolder(private val binding: ItemAuthorBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val author = authors[position]
                    listener(author)
                }
            }
        }
        fun bind(author: Author) = with(binding){
            tvName.text = author.name
            rbRating.rating = author.rating.toFloat()
        }

    }
}