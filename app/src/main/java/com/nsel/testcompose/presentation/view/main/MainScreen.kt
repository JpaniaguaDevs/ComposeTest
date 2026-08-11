package com.nsel.testcompose.presentation.view.main

import android.content.res.Configuration
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsel.testcompose.ui.theme.TestComposeTheme


data class Client(val name: String, val phone: String)
data class Invoice(val detail: String, val amount: Double)

@Composable
fun HomeScreen(){
    val clients = listOf(
        Client("Juan Pérez", "555-1234"),
        Client("María López", "555-5678"),
        Client("Carlos García", "555-9012"),
        Client("Ana Martínez", "555-3456"),
        Client("Luis Rodríguez", "555-7890"),
        Client("Sofía Hernández", "555-2345"),
    )

    val invoices = List(15) { index ->
        Invoice("Factura de servicios #${10000 + index}", (index + 1) * 100.0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = "Últimos Clientes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(clients) { client ->
                ClientItem(client)
            }
        }

        Text(
            text = "Últimas Facturas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(invoices) { invoice ->
                InvoiceItem(invoice)
            }
        }
    }

}

@Composable
fun ClientItem(client: Client) {
    Card(
        modifier = Modifier.width(140.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ){
            Text(
                text = client.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = client.phone,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun InvoiceItem(invoice: Invoice) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = invoice.detail,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$${invoice.amount}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
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