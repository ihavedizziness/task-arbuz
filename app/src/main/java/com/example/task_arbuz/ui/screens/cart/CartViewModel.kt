package com.example.task_arbuz.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.domain.model.Product
import com.example.task_arbuz.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository,
): ViewModel() {

    private val _cartProductsState = MutableStateFlow<Resource<List<Product>>>(Resource.Initial)
    val cartProductsState = _cartProductsState.asStateFlow()

    init {
        fetchCartProducts()
    }

    private fun fetchCartProducts() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchCartProducts().collect { result ->
                _cartProductsState.value = result
            }
        }

    fun updateProductQuantity(productId: Int, quantity: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProductQuantityInCart(productId, quantity)
        }

}