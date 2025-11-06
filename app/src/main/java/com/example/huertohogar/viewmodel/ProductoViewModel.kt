package com.example.huertohogar.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.huertohogar.model.Producto

class ProductoViewModel : ViewModel() {
    var productos = mutableStateListOf<Producto>()
    var codigo = mutableStateOf("")
    var nombre = mutableStateOf("")
    var precio = mutableStateOf("")
    var stock = mutableStateOf("")
    var imagenUri = mutableStateOf<Uri?>(null)

    fun agregarProducto() {
        if (codigo.value.isNotBlank() && nombre.value.isNotBlank() &&
            precio.value.toDoubleOrNull() != null && stock.value.toIntOrNull() != null
        ) {
            productos.add(
                Producto(
                    codigo = codigo.value,
                    nombre = nombre.value,
                    precio = precio.value.toDouble(),
                    stock = stock.value.toInt(),
                    imagenUri = imagenUri.value?.toString()
                )
            )
            limpiarCampos()
        }
    }

    private fun limpiarCampos() {
        codigo.value = ""
        nombre.value = ""
        precio.value = ""
        stock.value = ""
        imagenUri.value = null
    }
}