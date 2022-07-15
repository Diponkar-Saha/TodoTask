package com.diponnkar.todotask.di

import android.content.Context
import androidx.room.Room
import com.diponnkar.todotask.data.TodoDao
import com.diponnkar.todotask.data.TodoDatabase
import com.diponnkar.todotask.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(@ApplicationContext context: Context): TodoDatabase =
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo_db"
        ).build()

    @Provides
    fun providesUserDao(todoDatabase: TodoDatabase): TodoDao = todoDatabase.dao

    @Provides
    @Singleton
    fun providesTodoRepo(todoDao: TodoDao): TodoRepository = TodoRepository(todoDao)

}
