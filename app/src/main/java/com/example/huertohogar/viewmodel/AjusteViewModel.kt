package com.example.huertohogar.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AjusteViewModel : ViewModel() {
    var notificaciones = mutableStateOf(true)
    var temaOscuro = mutableStateOf(false)
}