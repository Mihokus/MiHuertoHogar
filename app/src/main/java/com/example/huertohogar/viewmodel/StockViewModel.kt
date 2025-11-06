package com.example.huertohogar.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.huertohogar.model.ProductoStock

class StockViewModel : ViewModel() {
    var productos = mutableStateListOf(
        ProductoStock("Manzanas Fuji", 120),
        ProductoStock("Naranjas Valencia", 103),
        ProductoStock("Plátanos Cavendish", 90)
    )

    fun incrementarStock(index: Int) {
        productos[index] = productos[index].copy(cantidad = productos[index].cantidad + 1)
    }

    fun decrementarStock(index: Int) {
        val p = productos[index]
        if (p.cantidad > 0) productos[index] = p.copy(cantidad = p.cantidad - 1)
    }
}
