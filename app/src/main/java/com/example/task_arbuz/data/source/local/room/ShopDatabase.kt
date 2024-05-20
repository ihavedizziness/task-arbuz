package com.example.task_arbuz.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task_arbuz.data.source.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 3,
    exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}