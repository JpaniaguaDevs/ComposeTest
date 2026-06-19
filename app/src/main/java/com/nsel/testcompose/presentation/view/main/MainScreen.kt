package com.nsel.testcompose.presentation.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("¡Bienvenido al Inicio, llegaste desde el Login!")
    }
}

@Composable
fun ProfileScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("¡Bienvenido al Perfil, llegaste desde el Login!")
    }
}

@Composable
fun SettingsScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("¡Bienvenido a Configuración, llegaste desde el Login!")
    }
}