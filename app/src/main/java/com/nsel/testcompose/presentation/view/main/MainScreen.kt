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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsel.testcompose.ui.theme.TestComposeTheme
import java.util.Locale


data class Client(val name: String, val phone: String)
data class Invoice(val detail: String, val amount: Double, val isPaid: Boolean)

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
        Invoice("Factura de servicios #${10000 + index}", (index + 1) * 100.0, index % 3 != 0)
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
            items(clients) { client -> ClientCard(client) }
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
            items(invoices) { invoice -> InvoiceCard(invoice) }
        }
    }

}

@Composable
fun ClientCard(client: Client) {
    val initial = client.name.firstOrNull()?.toString() ?: "?"

    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // 1. Creamos las referencias (Equivalente a android:id="@+id/...")
            val (avatar, nameText, phoneText) = createRefs()

            // 2. Avatar centrado en la parte superior
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .constrainAs(avatar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initial,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            // 3. Nombre (Debajo del avatar)
            Text(
                text = client.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(nameText) {
                    // Equivalente a app:layout_constraintTop_toBottomOf="@id/avatar"
                    top.linkTo(avatar.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            // 4. Teléfono (Debajo del nombre)
            Text(
                text = client.phone,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.constrainAs(phoneText) {
                    // Equivalente a app:layout_constraintTop_toBottomOf="@id/nameText"
                    top.linkTo(nameText.bottom, margin = 2.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}

@Composable
fun InvoiceCard(invoice: Invoice) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = invoice.detail,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                StatusBadge(isPaid = invoice.isPaid)
            }

            Text(
                text = String.format(Locale.US, "$%.2f", invoice.amount),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun StatusBadge(isPaid: Boolean) {
    val bg = if (isPaid) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer
    val textColor = if (isPaid) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onErrorContainer

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 2.dp)
    ) {
        Text(
            text = if (isPaid) "Pagado" else "Pendiente",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = textColor
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