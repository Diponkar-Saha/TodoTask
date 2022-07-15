package com.diponnkar.todotask.data

import androidx.room.*
import com.diponnkar.todotask.data.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo : Todo)


    @Query("Select * from Todo")
    suspend fun getTodos() : List<Todo>

    @Update
    suspend fun updateData(toDoData: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}