package com.example.task_arbuz.data.repository

import android.util.Log
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.data.model.Product
import com.example.task_arbuz.data.source.local.room.ProductDao
import com.example.task_arbuz.domain.ProductRepository
import com.example.task_arbuz.util.toPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
): ProductRepository {

    override fun fetchProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading)
        try {
            val productEntities = productDao.getProducts()
            if (productEntities.isEmpty()) {
                emit(Resource.Empty)
            } else {
                Log.d("PopularRepoTag", "Caching products")
                emit(Resource.Success(productEntities.map { it.toPresentation() }))
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
        }
    }

}