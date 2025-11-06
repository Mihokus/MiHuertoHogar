package com.example.huertohogar.model

data class Producto (
    val codigo: String,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val imagenUri: String? = null
)
