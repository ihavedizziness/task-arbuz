package com.example.task_arbuz.data

import android.util.Log
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.core.func.Result
import com.example.task_arbuz.domain.model.Product
import com.example.task_arbuz.data.source.local.room.ProductDao
import com.example.task_arbuz.domain.repository.ProductRepository
import com.example.task_arbuz.util.toEntity
import com.example.task_arbuz.util.toPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override fun fetchProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading)
        try {
            productDao.getProducts().collect {
                if (it.isEmpty()) {
                    emit(Resource.Empty)
                } else {
                    Log.d("ShopRepoTag", "Caching products")
                    emit(Resource.Success(it.toPresentation()))
                }
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
        }
    }

    override fun fetchCartProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading)
        try {
            productDao.getProductsFromCart().collect {
                if (it.isEmpty()) {
                    emit(Resource.Empty)
                } else {
                    Log.d("ShopRepoTag", "Caching cart products")
                    emit(Resource.Success(it.toPresentation()))
                }
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
        }
    }

    override fun getProductById(productId: Int): Flow<Resource<Product>> = flow {
        emit(Resource.Loading)
        try {
            productDao.getProductById(productId).collect {
                if (it == null) {
                    emit(Resource.Empty)
                } else {
                    emit(Resource.Success(it.toPresentation()))
                }
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateProductQuantityInCart(
        productId: Int,
        quantity: Int
    ): Result<Throwable, Unit> {
        return try {
            productDao.updateProductQuantityInCart(productId, quantity)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Error(throwable)
        }
    }

    override suspend fun insertProducts(products: List<Product>): Result<Throwable, Unit> {
        return try {
            productDao.insertProducts(products.toEntity())
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Error(throwable)
        }
    }

}