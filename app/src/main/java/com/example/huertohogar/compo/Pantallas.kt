package com.example.huertohogar.compo
import ProductoViewModel
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.huertohogar.viewmodel.AjusteViewModel
import com.example.huertohogar.viewmodel.StockViewModel


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


@Composable
fun PantallaPrincipal(rootNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") { PantallaInicio(modifier = Modifier.padding(innerPadding)) }
            composable("stock") { PantallaStock() }
            composable("producto") { PantallaProducto() }
            composable("ajuste") { PantallaAjuste(rootNavController) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        PantallaItem("inicio", "Inicio", Icons.Filled.Home),
        PantallaItem("stock", "Stock", Icons.Filled.ShoppingCart),
        PantallaItem("producto", "Producto", Icons.Filled.AddCircle),
        PantallaItem("ajuste", "Ajuste", Icons.Filled.Settings)
    )
    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { pantalla ->
            NavigationBarItem(
                icon = { Icon(pantalla.icono, contentDescription = pantalla.titulo, modifier = Modifier.size(28.dp)) },
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
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Panel General", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 16.dp))

        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Top productos del Mes", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(8.dp))
                Text("• FR001 Manzanas Fuji   Ventas: 120")
                Text("• FR002 Naranjas Valencia Ventas: 103")
                Text("• FR003 Plátanos Cavendish Ventas: 90")
            }
        }
    }
}


@Composable
fun PantallaStock(stockViewModel: StockViewModel = viewModel()) {
    val productos = stockViewModel.productos

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Gestión de Stock", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 16.dp))

        productos.forEachIndexed { index, producto ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("Stock actual: ${producto.cantidad}", color = Color.Gray)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        FilledIconButton(
                            onClick = { stockViewModel.decrementarStock(index) },
                            modifier = Modifier.size(40.dp),
                            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = CircleShape
                        ) { Text("−", fontSize = 22.sp, color = Color.White) }

                        Spacer(Modifier.width(8.dp))

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


@Composable
fun PantallaProducto(productoViewModel: ProductoViewModel = viewModel()) {
    val context = LocalContext.current
    val productos = productoViewModel.productos
    var codigo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) imagenUri = uri
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Crear Producto", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = codigo, onValueChange = { codigo = it }, label = { Text("Código") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock inicial") }, modifier = Modifier.fillMaxWidth(), singleLine = true)

        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (imagenUri != null) Image(painter = rememberAsyncImagePainter(imagenUri), contentDescription = null, modifier = Modifier.fillMaxSize())
            else Text("Foto del producto")
        }

        Spacer(Modifier.height(8.dp))
        Button(onClick = { launcher.launch("image/*") }) { Text("Seleccionar foto") }

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                if (codigo.isNotBlank() && nombre.isNotBlank() && precio.toDoubleOrNull() != null && stock.toIntOrNull() != null) {
                    productoViewModel.agregarProducto()
                    codigo = ""; nombre = ""; precio = ""; stock = ""; imagenUri = null
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) { Text("Crear Producto") }

        Spacer(Modifier.height(16.dp))
        productos.forEach { producto ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (producto.imagenUri != null) {
                        Image(painter = rememberAsyncImagePainter(producto.imagenUri), contentDescription = null, modifier = Modifier.size(60.dp))
                        Spacer(Modifier.width(8.dp))
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
@Composable
fun PantallaAjuste(navController: androidx.navigation.NavHostController, ajusteViewModel: AjusteViewModel = viewModel()) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Configuración y Ajustes", style = MaterialTheme.typography.headlineSmall)
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


