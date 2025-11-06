import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.huertohogar.model.Producto

class ProductoViewModel : ViewModel() {
    val productos = mutableStateListOf<Producto>()
    val codigo = mutableStateOf("")
    val nombre = mutableStateOf("")
    val precio = mutableStateOf("")
    val stock = mutableStateOf("")
    val imagenUri = mutableStateOf<Uri?>(null)

    fun agregarProducto() {

        val precioDouble = precio.value.toDoubleOrNull() ?: return
        val stockInt = stock.value.toIntOrNull() ?: return

        productos.add(
            Producto(
                codigo = codigo.value,
                nombre = nombre.value,
                precio = precioDouble,
                stock = stockInt,
                imagenUri = imagenUri.value?.toString()
            )
        )


        codigo.value = ""
        nombre.value = ""
        precio.value = ""
        stock.value = ""
        imagenUri.value = null
    }
}
