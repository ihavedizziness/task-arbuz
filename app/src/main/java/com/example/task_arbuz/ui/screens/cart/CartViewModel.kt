package com.example.task_arbuz.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.data.model.Product
import com.example.task_arbuz.domain.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository,
): ViewModel() {

    private val _cartProductsState = MutableStateFlow<Resource<List<Product>>>(Resource.Loading)
    val cartProductsState = _cartProductsState.asStateFlow()

    init {
        fetchCartProducts()
    }

    private fun fetchCartProducts() = viewModelScope.launch {
        repository.fetchCartProducts().collect { result ->
            _cartProductsState.value = result
        }
    }

}