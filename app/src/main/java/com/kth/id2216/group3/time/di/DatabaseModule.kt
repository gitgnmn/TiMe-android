package com.kth.id2216.group3.time.di

import android.content.Context
import com.kth.id2216.group3.time.data.dao.CategoryDAO
import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.db.TiMeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

        @Singleton
        @Provides
        fun provideTiMeDatabase(@ApplicationContext context: Context): TiMeDatabase {
            return TiMeDatabase.getDatabase(context)
        }

        @Provides
        fun provideTimerDao(tiMeDatabase: TiMeDatabase): TimerDAO {
            return tiMeDatabase.timerDAO()
        }

        @Provides
        fun provideCategoryDao(tiMeDatabase: TiMeDatabase): CategoryDAO {
            return tiMeDatabase.categoryDAO()
        }

}