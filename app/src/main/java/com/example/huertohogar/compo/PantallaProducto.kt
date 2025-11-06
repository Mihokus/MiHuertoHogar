package com.example.huertohogar.compo

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar.viewmodel.ProductoViewModel

@Composable
fun PantallaProducto(modifier: Modifier = Modifier, productoViewModel: ProductoViewModel = viewModel()) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        productoViewModel.imagenUri.value = uri
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Producto", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = productoViewModel.codigo.value,
            onValueChange = { productoViewModel.codigo.value = it },
            label = { Text("Código") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = productoViewModel.nombre.value,
            onValueChange = { productoViewModel.nombre.value = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = productoViewModel.precio.value,
            onValueChange = { productoViewModel.precio.value = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = productoViewModel.stock.value,
            onValueChange = { productoViewModel.stock.value = it },
            label = { Text("Stock") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (productoViewModel.imagenUri.value != null) {
                Image(
                    painter = rememberAsyncImagePainter(productoViewModel.imagenUri.value),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text("Foto del producto")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { launcher.launch("image/*") }) { Text("Seleccionar foto") }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { productoViewModel.agregarProducto() },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) { Text("Crear Producto") }

        Spacer(modifier = Modifier.height(16.dp))
        productoViewModel.productos.forEach { producto ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (producto.imagenUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(producto.imagenUri),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Column {
                        Text("${producto.codigo} - ${producto.nombre}")
                        Text("Precio: $${producto.precio} | Stock: ${producto.stock}")
                    }
                }
            }
        }
    }
}