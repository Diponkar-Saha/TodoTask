package com.diponnkar.todotask.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.diponnkar.todotask.data.Todo
import com.diponnkar.todotask.databinding.FragmentDetailsTodoBinding
import com.diponnkar.todotask.ui.edit.EditTodoFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsTodoFragment : Fragment() {
    private var _binding: FragmentDetailsTodoBinding? = null

    private val binding get() = _binding!!
    private val args: EditTodoFragmentArgs by navArgs()

    private lateinit var todo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsTodoBinding.inflate(inflater, container, false)
        todo = args.todo

        binding.currentDescriptionEt.text = todo.discription
        binding.currentTitleEt.text = todo.title



        return binding.root
    }

}