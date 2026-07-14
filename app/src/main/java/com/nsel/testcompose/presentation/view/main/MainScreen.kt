package com.nsel.testcompose.presentation.view.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsel.testcompose.ui.theme.TestComposeTheme

@Composable
fun HomeScreen(){
    val itemList = List(30) { "Elemento de prueba ${it + 1}" }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        items(itemList) { item ->
            ListItem(text = item)
        }
    }
}

@Composable
fun ListItem(text: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(
    name = "Home Modo Claro",
    showBackground = true,
    device = Devices.PHONE
)
@Preview(
    name = "Home Modo Oscuro",
    showBackground = true,
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewHomeScreen() {
    TestComposeTheme {
        HomeScreen()
    }
}

@Composable
fun ProfileScreen(){
    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface), contentAlignment = Alignment.Center) {
        Text(modifier= Modifier.padding(20.dp), color = MaterialTheme.colorScheme.onSurface, text ="¡Bienvenido al Perfil!")
    }
}
@Preview(
    name = "Profile Modo Claro",
    showBackground = true,
    device = Devices.PHONE
)
@Preview(
    name = "Profile Modo Oscuro",
    showBackground = true,
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewProfileScreen() {
    TestComposeTheme {
        ProfileScreen()
    }
}

@Composable
fun SettingsScreen(){
    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface), contentAlignment = Alignment.Center) {
        Text(modifier= Modifier.padding(20.dp), color = MaterialTheme.colorScheme.onSurface, text ="¡Bienvenido a Configuración!")
    }
}
@Preview(
    name = " Settings Modo Claro",
    showBackground = true,
    device = Devices.PHONE
)
@Preview(
    name = "Settings Modo Oscuro",
    showBackground = true,
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewSettingsScreen() {
    TestComposeTheme {
        SettingsScreen()
    }
}