package com.kth.id2216.group3.time.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kth.id2216.group3.time.data.db.TiMeDatabase
import com.kth.id2216.group3.time.data.entities.Timer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimerDAOTest {

    private lateinit var tiMeDatabase: TiMeDatabase
    private lateinit var timerDAO: TimerDAO
    private val timerA = Timer(id = 0, name = "A", categoryId = 0)
    private val timerB = Timer(id = 1, name = "B", categoryId = 0)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        tiMeDatabase = Room.inMemoryDatabaseBuilder(context, TiMeDatabase::class.java).build()
        timerDAO = tiMeDatabase.timerDAO()

        // Insert the test timers
        timerDAO.insertAll(listOf(timerA, timerB))
    }

    @After
    fun closeDb() {
        tiMeDatabase.close()
    }

    @Test
    fun getAll() = runBlocking {
        val timerList = timerDAO.getAll().first()

        assertEquals(2, timerList.size)
    }


}