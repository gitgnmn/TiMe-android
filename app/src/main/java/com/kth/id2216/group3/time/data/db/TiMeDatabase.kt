package com.kth.id2216.group3.time.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.entities.Session
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.util.Converters

@Database(entities = [Timer::class, Session::class], version = 1)
@TypeConverters(Converters::class)
abstract class TiMeDatabase : RoomDatabase() {

    abstract fun timerDAO(): TimerDAO

    companion object {
        val DB_NAME = "time_app_database"

        @Volatile
        private var INSTANCE: RoomDatabase? = null

        //singleton pattern to ensure that the DB is instantiated only once
        fun getDatabase(context: Context): RoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            else {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TiMeDatabase::class.java,
                        DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
            }
        }
    }


}