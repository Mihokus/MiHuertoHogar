package com.example.huertohogar.compo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment

@Composable
fun PantallaInicio(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Panel General",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Top productos del Mes", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• FR001 Manzanas Fuji   Ventas: 120")
                Text("• FR002 Naranjas Valencia Ventas: 103")
                Text("• FR003 Plátanos Cavendish Ventas: 90")
                Text("• VR001 Zanahorias Orgánicas Ventas: 69")
                Text("• VR002 Pimientos Tricolores Ventas: 50")
            }
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Productos con Stock Crítico", style = MaterialTheme.typography.titleMedium, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• PO003 - Quinua Orgánica - 2 UN")
                Text("• PO001 - Miel Orgánica - 5 UN")
                Text("• PL001 - Leche Entera - 6 LT")
                Text("• FR002 - Naranjas Valencia - 6 KG")
                Text("• VR002 Pimientos Tricolores - 2 UN")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Pedidos Recientes", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Pedido #1021 - 5KG Manzanas Fuji")
                Text("• Pedido #1020 - 3KG Naranjas Valencia")
                Text("• Pedido #1019 - 2KG Plátanos Cavendish")
            }
        }
    }
}
