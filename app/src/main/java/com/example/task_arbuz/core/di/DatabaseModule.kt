package com.example.task_arbuz.core.di

import android.content.Context
import androidx.room.Room
import com.example.task_arbuz.data.source.local.room.ProductDao
import com.example.task_arbuz.data.source.local.room.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ShopDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = ShopDatabase::class.java,
            name = "shop.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductDao(database: ShopDatabase): ProductDao = database.productDao()

}