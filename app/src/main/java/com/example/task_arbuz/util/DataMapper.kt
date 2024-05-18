package com.example.task_arbuz.util

import com.example.task_arbuz.data.model.Product
import com.example.task_arbuz.data.source.local.entity.ProductEntity

internal fun ProductEntity.toPresentation() =
    Product(
        id = id,
        name = name,
        price = price,
        image = image,
        quantity = quantity
    )