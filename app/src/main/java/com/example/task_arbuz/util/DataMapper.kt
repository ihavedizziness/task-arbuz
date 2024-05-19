package com.example.task_arbuz.util

import com.example.task_arbuz.data.model.Product
import com.example.task_arbuz.data.source.local.entity.ProductEntity

fun ProductEntity.toPresentation() =
    Product(
        id = id,
        name = name,
        price = price,
        image = image,
        quantity = quantity,
        cartQuantity = cartQuantity
    )

fun List<ProductEntity>.toPresentation(): List<Product> {
    return map { it.toPresentation() }
}

fun Product.toEntity() =
    ProductEntity(
        id = id,
        name = name,
        price = price,
        image = image,
        quantity = quantity,
        cartQuantity = cartQuantity
    )

fun List<Product>.toEntity(): List<ProductEntity> {
    return map { it.toEntity() }
}
