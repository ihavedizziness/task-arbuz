package com.example.task_arbuz.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val image: String? = null,
    val price: Double,
    val quantity: Int? = null,
    val cartQuantity: Int = 0,
)
