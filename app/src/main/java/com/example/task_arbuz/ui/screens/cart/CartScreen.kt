package com.example.task_arbuz.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.ui.component.CartListItem
import com.example.task_arbuz.ui.component.CircularLoading

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartProductsState by viewModel.cartProductsState.collectAsState()

    when (cartProductsState) {
        is Resource.Loading -> {
            CircularLoading()
        }
        is Resource.Error -> {}
        is Resource.Empty -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your shopping cart is empty",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        is Resource.Success -> {
            val cartProducts = (cartProductsState as Resource.Success).data
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(cartProducts) { item ->
                    CartListItem(cartProduct = item)
                }
            }
        }
    }
}