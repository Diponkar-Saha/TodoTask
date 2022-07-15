package com.diponnkar.todotask.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diponnkar.todotask.R
import com.diponnkar.todotask.adapter.TodoAdapter
import com.diponnkar.todotask.databinding.FragmentTodolistBinding
import com.diponnkar.todotask.viewmodel.TodoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment() {

    private lateinit var fragmentTodolistBinding: FragmentTodolistBinding
    private val todoViewModel: TodoViewModel by activityViewModels()
    lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTodolistBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_todolist, container, false)

        return fragmentTodolistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentTodolistBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = todoViewModel
            fragment = this@TodoListFragment
        }

        todoAdapter = TodoAdapter { todo ->
            MaterialAlertDialogBuilder(this.requireActivity())
                .setTitle(getString(R.string.alert))
                .setMessage(getString(R.string.do_u_want_delete))
                .setNegativeButton(getString(R.string.delete)) { dialog, where ->
                    todoViewModel.deleteTodo(todo)
                    todoViewModel.getTodos()
                    dialog.dismiss()
                }
                .setNeutralButton(getString(R.string.cancel)) { dialog, where ->
                    dialog.dismiss()
                }
                .show()



        }

        setUpRecyclerView()

        todoViewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            todos?.let {
                todoAdapter.submitList(todos)
            }
        })

    }

    private fun setUpRecyclerView() {
        fragmentTodolistBinding.recycler.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        todoViewModel.getTodos()
    }

    fun navigateToAddTodo() {
        findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
    }
}