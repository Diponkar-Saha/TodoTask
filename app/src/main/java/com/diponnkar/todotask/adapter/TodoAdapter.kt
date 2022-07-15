package com.diponnkar.todotask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diponnkar.todotask.data.Todo
import com.diponnkar.todotask.databinding.TodoItemBinding
import com.diponnkar.todotask.ui.list.TodoListFragmentDirections

class TodoAdapter(val imageOnCLick: (Todo)-> Unit) : ListAdapter<Todo, TodoAdapter.TodoHolder>(
    Companion
) {

    companion object: DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(
            oldItem: Todo,
            newItem: Todo
        ): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Todo,
            newItem: Todo
        ): Boolean {
            return oldItem==newItem
        }
    }



    inner class TodoHolder(val todoItemBinding: TodoItemBinding) : RecyclerView.ViewHolder(todoItemBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TodoHolder(TodoItemBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val todo = getItem(position)
        holder.todoItemBinding.apply {
            title.text = todo.title
            discription.text = todo.discription
            delete.setOnClickListener {
                imageOnCLick(todo)
            }
            edit.setOnClickListener { mView->
                val direction = TodoListFragmentDirections.actionTodoListFragmentToEditTodoFragment(todo)
                mView.findNavController().navigate(direction)

            }
            holder.itemView.setOnClickListener { mView->
                val direction = TodoListFragmentDirections.actionTodoListFragmentToDetailsTodoFragment(todo)
                mView.findNavController().navigate(direction)

            }
        }

    }
}