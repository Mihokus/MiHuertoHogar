package com.example.huertohogar.compo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar.viewmodel.AjusteViewModel

@Composable
fun PantallaAjuste(navController: androidx.navigation.NavHostController, ajusteViewModel: AjusteViewModel = viewModel()) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("⚙️ Configuración y Ajustes", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Notificaciones", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = ajusteViewModel.notificaciones.value, onCheckedChange = { ajusteViewModel.notificaciones.value = it })
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Tema Oscuro", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = ajusteViewModel.temaOscuro.value, onCheckedChange = { ajusteViewModel.temaOscuro.value = it })
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("Login") { popUpTo(navController.graph.startDestinationId) { inclusive = true } } },
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) { Text("Cerrar sesión", fontSize = 25.sp) }
    }
}