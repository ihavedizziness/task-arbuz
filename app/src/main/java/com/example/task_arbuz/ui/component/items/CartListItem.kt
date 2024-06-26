package com.example.task_arbuz.ui.component.items

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task_arbuz.R
import com.example.task_arbuz.domain.model.Product
import com.example.task_arbuz.ui.component.ImageNetworkLoader
import com.example.task_arbuz.ui.component.ProductItemButton
import com.example.task_arbuz.ui.screens.cart.CartViewModel
import com.example.task_arbuz.ui.theme.ButtonGrey

@Composable
fun CartListItem(
    cartProduct: Product
) {
    Column {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 18.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        1.dp,
                        Color.LightGray.copy(alpha = .2f),
                        MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(130.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    ImageNetworkLoader(
                        imageUrl = cartProduct.image,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp)
                ) {
                    Text(
                        text = cartProduct.name,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "$${cartProduct.price}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.height(45.dp))
                    ProductItemButton(product = cartProduct)
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}