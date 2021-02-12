package com.kth.id2216.group3.time.data.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.kth.id2216.group3.time.data.db.TiMeDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test

class CategoryDAOTest {

    private lateinit var tiMeDatabase: TiMeDatabase

    @Before
    fun initDb() {
        tiMeDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                TiMeDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        tiMeDatabase.close()
    }

}