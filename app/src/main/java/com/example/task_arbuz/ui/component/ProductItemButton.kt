package com.example.task_arbuz.ui.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task_arbuz.R
import com.example.task_arbuz.domain.model.Product
import com.example.task_arbuz.ui.screens.cart.CartViewModel
import com.example.task_arbuz.ui.theme.ButtonGrey

@Composable
fun ProductItemButton(
    product: Product,
    viewModel: CartViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val itemCount = product.cartQuantity

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(24.dp))
            .background(if (itemCount > 0) Color.Green else ButtonGrey)
            .clickable {
                viewModel.updateProductQuantity(product.id, 1)
            }
    ) {
        if (itemCount >= 1) {
            IconButton(
                onClick = {
                    viewModel.updateProductQuantity(product.id, itemCount - 1)
                }
            ) {
                Icon(
                    painter = painterResource(id = if (itemCount > 1) R.drawable.ic_remove else R.drawable.ic_delete),
                    contentDescription = "Remove from basket",
                    tint = Color.White
                )
            }
            Text(
                text = itemCount.toString(),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            IconButton(
                onClick = {
                    if (itemCount < product.quantity) {
                        viewModel.updateProductQuantity(product.id, itemCount + 1)
                    } else {
                        Toast.makeText(context, "Maximum quantity reached", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        } else {
            Text(
                text = "Add item",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add to basket",
                tint = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            )
        }
    }
}