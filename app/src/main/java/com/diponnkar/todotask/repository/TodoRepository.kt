package com.diponnkar.todotask.repository

import com.diponnkar.todotask.data.Todo
import com.diponnkar.todotask.data.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    suspend fun insertTodo(todo : Todo){
        todoDao.insertTodo(todo)
    }

    suspend fun getTodos() : List<Todo> = withContext(Dispatchers.Main){
        todoDao.getTodos()
    }

    suspend fun updateData(todo: Todo){
        todoDao.updateData(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }
}