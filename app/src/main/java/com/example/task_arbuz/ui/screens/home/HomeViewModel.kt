package com.example.task_arbuz.ui.screens.home

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
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    private val _productsState = MutableStateFlow<Resource<List<Product>>>(Resource.Loading)
    val productsState = _productsState.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() = viewModelScope.launch {
        repository.fetchProducts().collect { result ->
            _productsState.value = result
        }
    }

    fun addProducts(products: List<Product>) = viewModelScope.launch {
        repository.insertProducts(products)
    }

}