package com.example.huertohogar.compo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import com.example.huertohogar.R

@Composable
fun FormularioValidacion(modifier: Modifier = Modifier) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }


    var mensajeExito by remember { mutableStateOf("") }

    var emailCorrecto = "adminHuerto@gmail.com"
    var contraseñaCorrecta = "Huerto1234"

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.huerto),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .padding(8.dp)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = ""
            },
            label = { Text("Correo") },
            isError = emailError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
            },
            label = { Text("Contraseña") },
            isError = passwordError.isNotEmpty(),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )


        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                emailError = ""
                passwordError = ""
                mensajeExito = ""



                if (email.isBlank()) {
                    emailError = "El email no puede estar vacio"
                } else if (!email.contains("@") || !email.contains(".")) {
                    emailError = "El email no es valido"
                }


                if (password.isBlank()) {
                    passwordError = "La contraseña no puede estar vacia"
                } else if (password.length < 6) {
                    passwordError = "La contraseña debe tener al menos 6 caracteres"
                }

                if (emailError.isEmpty() && passwordError.isEmpty()) {
                    mensajeExito = "Formulario enviado correctamente"
                }
                if (emailError.isEmpty() && passwordError.isEmpty()){
                    if (email == emailCorrecto && password == contraseñaCorrecta){
                        mensajeExito = "Inicio de sesion exitoso"
                    } else {
                        mensajeExito = "Error en las credenciales"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp)

        ) {
            Text("Iniciar sesión", fontSize = 25.sp)
        }

        if (mensajeExito.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = mensajeExito,
                color = Color.Green,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}