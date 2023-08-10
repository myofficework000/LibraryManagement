package com.example.libreasy.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.libreasy.database.entity.Book
import com.example.libreasy.databinding.ItemBookBinding

class BookListAdapter(var books:List<Book>, private val listener: (book: Book) -> Unit) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(){
    private lateinit var binding: ItemBookBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookListAdapter.BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemBookBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListAdapter.BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size

    inner class BookViewHolder(private val binding: ItemBookBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val book = books[position]
                    listener(book)
                }
            }
        }
        fun bind(book: Book) = with(binding){
            tvTitle.text = book.title
            tvAuthor.text = book.author
            tvYear.text = book.year
            rbRating.rating = book.rating.toFloat()
        }

    }
}