package com.example.huertohogar.compo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar.viewmodel.StockViewModel

@Composable
fun PantallaStock(modifier: Modifier = Modifier, stockViewModel: StockViewModel = viewModel()) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Gestión de Stock", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 16.dp))

        stockViewModel.productos.forEachIndexed { index, producto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("Stock actual: ${producto.cantidad}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        FilledIconButton(
                            onClick = { stockViewModel.decrementarStock(index) },
                            modifier = Modifier.size(40.dp),
                            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = CircleShape
                        ) { Text("−", fontSize = 22.sp, color = Color.White) }

                        Spacer(modifier = Modifier.width(8.dp))

                        FilledIconButton(
                            onClick = { stockViewModel.incrementarStock(index) },
                            modifier = Modifier.size(40.dp),
                            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = CircleShape
                        ) { Text("+", fontSize = 22.sp, color = Color.White) }
                    }
                }
            }
        }
    }
}