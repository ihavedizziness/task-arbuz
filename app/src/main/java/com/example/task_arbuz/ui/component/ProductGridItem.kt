package com.example.task_arbuz.ui.component

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task_arbuz.R
import com.example.task_arbuz.data.model.Product
import com.example.task_arbuz.ui.theme.ButtonGrey

@Composable
fun ProductGridItem(
    product: Product
) {
    var buttonState by remember { mutableStateOf<ButtonState>(ButtonState.Default) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    ImageNetworkLoader(
                        imageUrl = product.image ?: "",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = product.name,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                        ),
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "$${product.price}",
                        style =  TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp))
                            .background(if (buttonState is ButtonState.Basket) Color.Green else ButtonGrey)
                            .clickable {
                                if (buttonState is ButtonState.Default) {
                                    buttonState = ButtonState.Basket(1)
                                }
                            }
                    ) {
                        when (buttonState) {
                            is ButtonState.Basket -> {
                                val itemCount = (buttonState as ButtonState.Basket).itemCount
                                IconButton(
                                    onClick = {
                                        buttonState = if (itemCount > 1) {
                                            ButtonState.Basket(itemCount - 1)
                                        } else {
                                            ButtonState.Default
                                        }
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
                                )
                                IconButton(
                                    onClick = {
                                        buttonState = ButtonState.Basket(itemCount + 1)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add",
                                        tint = Color.White
                                    )
                                }
                            }
                            else -> {
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
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

sealed class ButtonState {
    data object Default : ButtonState()
    data class Basket(val itemCount: Int) : ButtonState()
}