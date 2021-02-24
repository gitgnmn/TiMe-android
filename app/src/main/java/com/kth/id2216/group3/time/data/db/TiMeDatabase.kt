package com.kth.id2216.group3.time.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kth.id2216.group3.time.data.dao.CategoryDAO
import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.entities.Session
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.util.Converters

/**
 * Handle the database singleton
 */
@Database(entities = [Timer::class, Session::class, Category::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TiMeDatabase : RoomDatabase() {

    abstract fun timerDAO(): TimerDAO
    abstract fun categoryDAO(): CategoryDAO

    companion object {
        val DB_NAME = "time_app_database"

        @Volatile
        private var INSTANCE: TiMeDatabase? = null

        //singleton pattern to ensure that the DB is instantiated only once
        fun getDatabase(context: Context): TiMeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            else {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TiMeDatabase::class.java,
                        DB_NAME
                ).fallbackToDestructiveMigration(
                ).build()
                INSTANCE = instance
                return instance
            }
            }
        }
    }


}