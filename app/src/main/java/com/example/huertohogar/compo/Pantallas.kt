package com.example.huertohogar.compo
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment


@Composable
fun NavegacionPantallas(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Login",
        modifier = modifier
    ) {

        composable("Login") {
            FormularioValidacion(
                loginExitoso = {
                    navController.navigate("principal") {
                        popUpTo("Login") { inclusive = true }
                    }
                }
            )
        }
        composable("principal") {
            PantallaPrincipal(rootNavController = navController)
        }
    }
}
@Composable()
fun PantallaPrincipal(rootNavController: androidx.navigation.NavHostController) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") { PantallaInicio() }
            composable("stock") { PantallaStock() }
            composable("producto") { PantallaProducto() }
            composable("ajuste") { PantallaAjuste(rootNavController) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: androidx.navigation.NavHostController) {
    val items = listOf(
        PantallaItem("inicio", "Inicio",Icons.Filled.Home),
        PantallaItem("stock", "Stock",Icons.Filled.ShoppingCart),
        PantallaItem("producto", "Producto",Icons.Filled.AddCircle),
        PantallaItem("ajuste", "Ajuste",Icons.Filled.Settings)
    )

    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { pantalla ->
            NavigationBarItem(
                icon = { Icon(pantalla.icono, contentDescription = pantalla.titulo, modifier = Modifier.size(28.dp))},
                label = { Text(pantalla.titulo) },
                selected = currentDestination?.route == pantalla.ruta,
                onClick = {
                    navController.navigate(pantalla.ruta) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

data class PantallaItem(val ruta: String, val titulo: String, val icono: ImageVector)

@Composable
fun PantallaInicio() {
    Text("", style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun PantallaStock() {
    Text("", style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun PantallaProducto() {
    Text("", style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun PantallaAjuste(navController: androidx.navigation.NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("⚙️ Configuración y Ajustes", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navController.navigate("Login") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }) {
            Text("Cerrar sesión")
        }
    }
}









