package com.example.task_arbuz.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.task_arbuz.data.source.local.entity.ProductEntity
import com.example.task_arbuz.util.Constants.DB_VERSION
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Database(
    entities = [ProductEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) return emptyList()
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(list: List<Int>?): String? {
        if (list == null) return null
        return Json.encodeToString(list)
    }
}