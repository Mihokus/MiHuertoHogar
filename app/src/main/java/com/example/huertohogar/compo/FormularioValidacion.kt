package com.example.huertohogar.compo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.huertohogar.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar.viewmodel.LoginViewModel

@Composable
fun FormularioValidacion(
    loginViewModel: LoginViewModel = viewModel(),
    loginExitoso: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.huerto),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = loginViewModel.email.value,
            onValueChange = { loginViewModel.email.value = it },
            label = { Text("Correo") },
            isError = loginViewModel.emailError.value.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        if (loginViewModel.emailError.value.isNotEmpty()) {
            Text(loginViewModel.emailError.value, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.password.value,
            onValueChange = { loginViewModel.password.value = it },
            label = { Text("Contraseña") },
            isError = loginViewModel.passwordError.value.isNotEmpty(),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        if (loginViewModel.passwordError.value.isNotEmpty()) {
            Text(loginViewModel.passwordError.value, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { loginViewModel.onLogin(loginExitoso) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Iniciar sesión", fontSize = 25.sp)
        }

        if (loginViewModel.mensajeExito.value.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(loginViewModel.mensajeExito.value, color = Color.Green)
        }
    }
}