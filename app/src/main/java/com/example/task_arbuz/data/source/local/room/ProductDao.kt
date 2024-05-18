package com.example.task_arbuz.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.task_arbuz.data.source.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(entities: List<ProductEntity>)

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductEntity>

    @Query("DELETE FROM products")
    suspend fun clearProducts()

    @Transaction
    suspend fun clearAndInsertProducts(entities: List<ProductEntity>) {
        clearProducts()
        insertProducts(entities)
    }

}