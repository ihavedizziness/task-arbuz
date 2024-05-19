package com.example.task_arbuz.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val price: Double,
    val quantity: Int,
    val cartQuantity: Int,
)
