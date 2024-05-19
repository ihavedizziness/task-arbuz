package com.example.task_arbuz.ui.component.main

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
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    private val _productState = MutableStateFlow<Resource<Product>>(Resource.Loading)
    val productState = _productState.asStateFlow()

    fun getProductById(productId: Int) = viewModelScope.launch {
        repository.getProductById(productId).collect { result ->
            _productState.value = result
        }
    }

    fun updateProductQuantity(productId: Int, quantity: Int) = viewModelScope.launch {
        repository.updateProductQuantityInCart(productId, quantity)
    }

}