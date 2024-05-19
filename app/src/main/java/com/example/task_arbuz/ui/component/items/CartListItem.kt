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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.data.model.Product
import com.example.task_arbuz.ui.component.ImageNetworkLoader
import com.example.task_arbuz.ui.theme.ButtonGrey

@Composable
fun CartListItem(
    cartProduct: Product,
    viewModel: ProductViewModel = hiltViewModel(key = cartProduct.id.toString())
) {
    var itemCount by remember { mutableIntStateOf(cartProduct.cartQuantity) }
    val productState by viewModel.productState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProductById(cartProduct.id)
    }

    when (val state = productState) {
        is Resource.Success -> {
            itemCount = state.data.cartQuantity
        }
        else -> {}
    }

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
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp))
                            .background(if (itemCount > 0) Color.Green else ButtonGrey)
                            .clickable {
                                if (itemCount == 0) {
                                    viewModel.updateProductQuantity(cartProduct.id, 1)
                                }
                                Log.d("count", itemCount.toString())
                            }
                    ) {
                        if (itemCount >= 1) {
                            IconButton(
                                onClick = {
                                    viewModel.updateProductQuantity(
                                        cartProduct.id,
                                        itemCount - 1
                                    )
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
                                    .padding(vertical = 4.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            IconButton(
                                onClick = {
                                    if (itemCount < cartProduct.quantity) {
                                        viewModel.updateProductQuantity(
                                            cartProduct.id,
                                            itemCount + 1
                                        )
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
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}