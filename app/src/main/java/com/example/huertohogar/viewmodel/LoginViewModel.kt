package com.example.huertohogar.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var emailError = mutableStateOf("")
    var passwordError = mutableStateOf("")
    var mensajeExito = mutableStateOf("")

    private val emailCorrecto = "adminHuerto@gmail.com"
    private val contraseñaCorrecta = "Huerto1234"

    fun onLogin(loginExitoso: () -> Unit) {
        emailError.value = ""
        passwordError.value = ""
        mensajeExito.value = ""

        if (email.value.isBlank()) emailError.value = "El email no puede estar vacío"
        else if (!email.value.contains("@") || !email.value.contains(".")) emailError.value = "El email no es válido"

        if (password.value.isBlank()) passwordError.value = "La contraseña no puede estar vacía"
        else if (password.value.length < 6) passwordError.value = "La contraseña debe tener al menos 6 caracteres"

        if (emailError.value.isEmpty() && passwordError.value.isEmpty()) {
            if (email.value == emailCorrecto && password.value == contraseñaCorrecta) {
                mensajeExito.value = "Inicio de sesión exitoso"
                loginExitoso()
            } else {
                mensajeExito.value = "Error en las credenciales"
            }
        }
    }
}