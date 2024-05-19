package com.example.task_arbuz.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.data.model.Product
import com.example.task_arbuz.ui.component.ProductGridItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val productsState by viewModel.productsState.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(
                text = "Products",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 18.dp, bottom = 15.dp)
            )
        }

        handleProductsState(
            productsState = productsState
        )
    }
}

fun LazyGridScope.handleProductsState(
    productsState: Resource<List<Product>>
) {
    when (productsState) {
        is Resource.Loading -> {}
        is Resource.Empty -> {}
        is Resource.Error -> {}
        is Resource.Success -> {
            val products = productsState.data

            items(products) { item ->
                ProductGridItem(product = item)
            }
        }
    }
}
