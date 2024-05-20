package com.example.task_arbuz.domain.model

data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val price: Double,
    val quantity: Int,
    val cartQuantity: Int,
)
