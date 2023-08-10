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
import com.example.libreasy.database.dao.BookDao
import com.example.libreasy.database.entity.Book
import com.example.libreasy.databinding.FragmentAddBookBinding
import com.example.libreasy.databinding.FragmentHomeBinding


class AddBookFragment : Fragment() {
    private lateinit var binding: FragmentAddBookBinding
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
        binding = FragmentAddBookBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnAddBook.setOnClickListener {
                val dummyText = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim."
                val book = Book(title=etTitle.text.toString(),author=etAuthor.text.toString(),genre=etGenre.text.toString(), rating=etRating.text.toString().toInt(), year=etYear.text.toString(), description=dummyText)
                bookDao.addBook(book)
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                etTitle.text?.clear()
                etAuthor.text?.clear()
                etGenre.text?.clear()
                etRating.text?.clear()
                etYear.text?.clear()
                etDescription.text?.clear()

                requireActivity().supportFragmentManager.popBackStack()

            }
        }

    }

    private fun initDao() {
        databaseHelper = DatabaseHelper(requireContext())
        bookDao = BookDao(databaseHelper)
    }

}