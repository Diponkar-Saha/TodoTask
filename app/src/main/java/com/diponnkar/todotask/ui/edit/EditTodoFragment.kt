package com.diponnkar.todotask.ui.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.diponnkar.todotask.R
import com.diponnkar.todotask.data.Todo
import com.diponnkar.todotask.databinding.FragmentEditTodoBinding
import com.diponnkar.todotask.databinding.FragmentTodolistBinding
import com.diponnkar.todotask.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditTodoFragment : Fragment() {
    private var _binding: FragmentEditTodoBinding? = null

    private val binding get() = _binding!!
    private val args: EditTodoFragmentArgs by navArgs()
    private val todoViewModel: TodoViewModel by activityViewModels()
    private lateinit var todo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        todo = args.todo

        binding.currentDescriptionEt.setText(todo.discription)
        binding.currentTitleEt.setText(todo.title)

        binding.update.setOnClickListener {
            val updatedItem = Todo(
                todo.id,
                binding.currentTitleEt.text.toString(),
                binding.currentDescriptionEt.text.toString()
            )
            todoViewModel.updateData(updatedItem)
            findNavController().navigate(R.id.action_editTodoFragment_to_todoListFragment)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()

        }




        return binding.root
    }


}