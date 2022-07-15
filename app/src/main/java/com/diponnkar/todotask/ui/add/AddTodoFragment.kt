package com.diponnkar.todotask.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.diponnkar.todotask.R
import com.diponnkar.todotask.data.Todo
import com.diponnkar.todotask.databinding.FragmentAddTodoBinding
import com.diponnkar.todotask.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment() : Fragment() {

    private lateinit var fragmentAddTodoBinding: FragmentAddTodoBinding
    private val todoViewModel: TodoViewModel by activityViewModels()
    lateinit var todo : Todo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddTodoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_todo, container, false)

        return fragmentAddTodoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentAddTodoBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = todoViewModel
            addTodoFragemnt = this@AddTodoFragment
        }
    }

    fun addTodo(){
        todoViewModel.addTodo(Todo(null,fragmentAddTodoBinding.title.text.toString(),fragmentAddTodoBinding.description.text.toString()))
        findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
    }
}