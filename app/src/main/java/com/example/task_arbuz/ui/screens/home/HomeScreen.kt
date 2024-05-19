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
import com.example.task_arbuz.ui.component.items.ProductGridItem

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
            productsState = productsState,
            viewModel = viewModel
        )
    }
}

fun LazyGridScope.handleProductsState(
    productsState: Resource<List<Product>>,
    viewModel: HomeViewModel
) {
    when (productsState) {
        is Resource.Loading -> {}
        is Resource.Empty -> {
            val products = listOf(
                Product(1, "Avocado", "https://cdn.pixabay.com/photo/2024/01/09/22/11/avocado-8498520_1280.jpg", 19.99, 10, 0),
                Product(2, "Carrots", "https://media.istockphoto.com/id/1388403435/photo/fresh-carrots-isolated-on-white-background.jpg?s=2048x2048&w=is&k=20&c=9sTXjB11hCYhq6VQVltZu3_QyHlygKVIbwi3iPVBhZw=", 29.99, 20, 0),
                Product(3, "Bread", "https://cdn.pixabay.com/photo/2018/06/10/20/30/bread-3467243_960_720.jpg", 39.99, 15, 0),
                Product(4, "Honey", "https://cdn.pixabay.com/photo/2020/04/14/18/13/honey-5043708_1280.jpg", 49.99, 3, 0),
                Product(5, "Ice Cream", "https://cdn.pixabay.com/photo/2017/11/30/08/56/ice-cream-2987955_1280.jpg", 59.99, 5, 0),
                Product(6, "Eggs", "https://cdn.pixabay.com/photo/2016/05/05/15/29/eggs-1374141_960_720.jpg", 69.99, 30, 0),
                Product(7, "Marshmallow", "https://cdn.pixabay.com/photo/2017/08/29/08/51/marshmallow-2692477_1280.jpg", 79.99, 25, 0),
                Product(8, "Nuts", "https://cdn.pixabay.com/photo/2017/11/22/22/53/nuts-2971675_1280.jpg", 89.99, 1, 0),
                Product(9, "Potatoes", "https://cdn.pixabay.com/photo/2018/05/29/23/18/potato-3440360_960_720.jpg", 99.99, 40, 0),
                Product(10, "Coffee", "https://cdn.pixabay.com/photo/2021/09/17/12/12/coffee-6632524_1280.jpg", 109.99, 12, 0)
            )

            viewModel.addProducts(products)
        }
        is Resource.Error -> {}
        is Resource.Success -> {
            val products = productsState.data

            items(products) { item ->
                ProductGridItem(product = item)
            }
        }
    }
}
