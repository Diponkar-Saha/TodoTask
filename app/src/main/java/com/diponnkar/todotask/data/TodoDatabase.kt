package com.diponnkar.todotask.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diponnkar.todotask.data.Todo
import com.diponnkar.todotask.data.TodoDao

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {

    abstract val dao : TodoDao
}