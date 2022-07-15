package com.diponnkar.todotask

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.diponnkar.todotask.data.Todo
import com.diponnkar.todotask.data.TodoDao
import com.diponnkar.todotask.data.TodoDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

    }
}


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TodoTestDao {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var todoTestDatabase: TodoDatabase
    private lateinit var todoTestDao: TodoDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        todoTestDatabase = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        todoTestDao = todoTestDatabase.dao
    }

    // execute after every test case
    @After
    fun teardown() {
        todoTestDatabase.close()
    }

    /*
    test case to insert user in room database
    */
    @Test
    fun insertUser() = runBlockingTest {
        val todo = Todo(null,"Some Random Title", "Some Random Description")
        todoTestDao.insertTodo(todo)
        val todos = todoTestDao.getTodos()
        assertThat(todos).contains(todo)
    }


    @Test
    fun deleteUser() = runBlockingTest {
        val todo = Todo(null,"Some Random Title1", "Some Random Description1")
        todoTestDao.insertTodo(todo)
        todoTestDao.deleteTodo(todo)
        val todos = todoTestDao.getTodos()
        assertThat(todos).doesNotContain(todo)
    }


    @Test
    fun updateUser() = runBlockingTest {
        val todo = Todo(null,"Some Random Title2", "Some Random Description3")
        todoTestDao.insertTodo(todo)
        val newTodo = todo.copy(title = "update title")
        todoTestDao.updateData(newTodo)
        val todos = todoTestDao.getTodos()
        assertThat(todos).contains(newTodo)
    }
}
