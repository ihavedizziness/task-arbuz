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

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: Int): ProductEntity?

    @Query("SELECT * FROM products WHERE cartQuantity > 0")
    suspend fun getProductsFromCart(): List<ProductEntity>

    @Query("UPDATE products SET cartQuantity = :quantity WHERE id = :productId")
    suspend fun updateProductQuantityInCart(productId: Int, quantity: Int)

    @Query("UPDATE products SET cartQuantity = 0 WHERE id = :productId")
    suspend fun removeProductFromCartById(productId: Int)

}