package com.example.huertohogar.compo
import androidx.compose.foundation.layout.padding
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
            PantallaPrincipal()
        }
    }
}
@Composable
fun PantallaPrincipal() {
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
            composable("ajuste") { PantallaAjuste() }
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

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { pantalla ->
            NavigationBarItem(
                icon = { Icon(pantalla.icono, contentDescription = pantalla.titulo)},
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
fun PantallaAjuste() {
    Text("", style = MaterialTheme.typography.headlineSmall)
}









