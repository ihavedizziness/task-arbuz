package com.example.task_arbuz.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var image: String,
    var price: Double,
    var quantity: Int,
    var cartQuantity: Int = 0
)