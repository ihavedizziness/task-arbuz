package com.example.task_arbuz.domain.repository

import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.core.func.Result
import com.example.task_arbuz.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun fetchProducts(): Flow<Resource<List<Product>>>
    fun fetchCartProducts(): Flow<Resource<List<Product>>>
    fun getProductById(productId: Int): Flow<Resource<Product>>
    suspend fun updateProductQuantityInCart(productId: Int, quantity: Int): Result<Throwable, Unit>
    suspend fun insertProducts(products: List<Product>): Result<Throwable, Unit>
}