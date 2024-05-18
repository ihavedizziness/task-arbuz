package com.example.task_arbuz.core.di

import com.example.task_arbuz.data.repository.ProductRepositoryImpl
import com.example.task_arbuz.data.source.local.room.ProductDao
import com.example.task_arbuz.domain.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDao
    ): ProductRepository {
        return ProductRepositoryImpl(
            productDao = productDao
        )
    }

}