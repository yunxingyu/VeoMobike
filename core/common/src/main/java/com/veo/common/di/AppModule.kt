package com.veo.common.di

import android.app.NotificationManager
import android.content.Context
import androidx.room.Room
import com.veo.common.database.VeoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil = DataStoreUtil(context)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VeoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            VeoDatabase::class.java,
            "Note_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTopicDao(noteDatabase: VeoDatabase) = noteDatabase.topicDao()

    @Provides
    @Singleton
    fun providerNotificationManager(@ApplicationContext context: Context) =
        context.getSystemService(NotificationManager::class.java)
}
