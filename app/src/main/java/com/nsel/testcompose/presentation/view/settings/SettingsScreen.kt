package com.nsel.testcompose.presentation.view.settings

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsel.testcompose.ui.theme.TestComposeTheme

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