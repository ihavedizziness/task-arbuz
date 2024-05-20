package com.example.task_arbuz.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.ui.component.items.CartListItem
import com.example.task_arbuz.ui.component.CircularLoading
import java.util.Locale

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartProductsState by viewModel.cartProductsState.collectAsState()

    when (cartProductsState) {
        is Resource.Loading -> {
            CircularLoading()
        }
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
            val totalPrice = cartProducts.sumOf { it.price * it.cartQuantity }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row {
                    Text(
                        text = "Total price: ",
                        style = TextStyle(
                            fontSize = 22.sp,
                            color = Color.Black,
                        ),
                        modifier = Modifier
                            .padding(start = 15.dp)
                    )
                    Text(
                        text = "$${String.format(Locale.US,"%.2f", totalPrice)}",
                        style = TextStyle(
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    items(
                        items = cartProducts,
                        key = {
                            product -> product.id
                        }
                    ) { item ->
                        CartListItem(cartProduct = item)
                    }
                }
            }
        }
        else -> {}
    }
}