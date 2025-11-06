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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
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
            modifier = Modifier.fillMaxWidth()
        ) {
            composable("inicio") { PantallaInicio(modifier = Modifier.padding(innerPadding))}
            composable("stock") { PantallaStock(modifier = Modifier.padding(innerPadding))}
            composable("producto") { PantallaProducto(modifier = Modifier.padding(innerPadding))}
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
fun PantallaInicio(modifier: Modifier= Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Panel General",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Top productos del Mes",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
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
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Productos con Stock Crítico",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Ejemplo de datos
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
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Pedidos Recientes",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Pedido #1021 - 5KG Manzanas Fuji ")
                Text("• Pedido #1020 - 3KG Naranjas Valencia ")
                Text("• Pedido #1019 - 2KG Plátanos Cavendish ")
            }
        }

    }
}


@Composable
fun PantallaStock(modifier: Modifier = Modifier) {
    val productos = remember {
        mutableStateListOf(
            ProductoStock("Manzanas Fuji", 120),
            ProductoStock("Naranjas Valencia", 103),
            ProductoStock("Plátanos Cavendish", 90),
            ProductoStock("Zanahorias Orgánicas", 69),
            ProductoStock("Pimientos Tricolores", 50)
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gestión de Stock",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        productos.forEachIndexed { index, producto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                        Text("Stock actual: ${producto.cantidad}", color = Color.Gray)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Botón circular morado (restar)
                        FilledIconButton(
                            onClick = {
                                if (producto.cantidad > 0) {
                                    productos[index] =
                                        producto.copy(cantidad = producto.cantidad - 1)
                                }
                            },
                            modifier = Modifier.size(40.dp),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = CircleShape
                        ) {
                            Text("−", fontSize = 22.sp, color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Botón circular morado (sumar)
                        FilledIconButton(
                            onClick = {
                                productos[index] =
                                    producto.copy(cantidad = producto.cantidad + 1)
                            },
                            modifier = Modifier.size(40.dp),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = CircleShape
                        ) {
                            Text("+", fontSize = 22.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

data class ProductoStock(val nombre: String, val cantidad: Int)




data class Producto(
    val codigo: String,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val imagenUri: String? = null
)

@Composable
fun PantallaProducto(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var codigo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }

    val productos = remember { mutableStateListOf<Producto>() }

    // Launcher para seleccionar imagen de la galería
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) imagenUri = uri
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Producto", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it },
            label = { Text("Código") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Stock inicial") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Vista previa de imagen
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (imagenUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imagenUri),
                    contentDescription = "Imagen del producto",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text("Foto del producto")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { launcher.launch("image/*") }) {
            Text("Seleccionar foto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (codigo.isNotBlank() && nombre.isNotBlank() && precio.toDoubleOrNull() != null && stock.toIntOrNull() != null) {
                    productos.add(
                        Producto(
                            codigo = codigo,
                            nombre = nombre,
                            precio = precio.toDouble(),
                            stock = stock.toInt(),
                            imagenUri = imagenUri?.toString()
                        )
                    )
                    // Limpiar campos
                    codigo = ""
                    nombre = ""
                    precio = ""
                    stock = ""
                    imagenUri = null
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Crear Producto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar productos creados
        productos.forEach { producto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
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

@Composable
fun PantallaAjuste(navController: androidx.navigation.NavHostController) {
    var notificaciones by remember { mutableStateOf(true) }
    var temaNegro by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("⚙️ Configuración y Ajustes", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        Text("Perfil", style = MaterialTheme.typography.titleMedium)
        Text("Usuario: adminHuerto@gmail.com")
        Text("Rol: Administrador")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Preferencias", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Notificaciones",fontSize= 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = notificaciones,
                onCheckedChange = { notificaciones = it }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Tema Oscuro", fontSize= 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = temaNegro,
                onCheckedChange = { temaNegro = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Información", style = MaterialTheme.typography.titleMedium)
        Text("Versión: 1.0.0")
        Text("Soporte: soporte@huertohogar.cl")

        Spacer(modifier = Modifier.height(24.dp))


        Button(onClick = {
            navController.navigate("Login") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)) {
            Text("Cerrar sesión",fontSize = 25.sp)
        }
    }
}









