package com.example.starwars.ui.presentation.screens.results.components.vehicles

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwars.domain.model.vehicle.VehicleItem

@Composable
fun VehicleCard(
    vehicle: VehicleItem,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title
            Text(
                text = vehicle.name ?: "Unnamed Vehicle",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Info Rows with safe fallback
            VehicleInfoRow("Model", vehicle.model ?: "Unknown")
            VehicleInfoRow("Manufacturer", vehicle.manufacturer ?: "Unknown")
            VehicleInfoRow("Cost in credits", vehicle.costInCredits ?: "Unknown")
            VehicleInfoRow("Length", vehicle.length ?: "Unknown")
            VehicleInfoRow("Crew", vehicle.crew ?: "Unknown")
            VehicleInfoRow("Passengers", vehicle.passengers ?: "Unknown")
            VehicleInfoRow("Cargo capacity", vehicle.cargoCapacity ?: "Unknown")
        }
    }
}

@Composable
fun VehicleInfoRow(
    label: String,
    value: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleCardPreview() {
    MaterialTheme {
        VehicleCard(
            vehicle =
                VehicleItem(
                    name = "Speeder Bike",
                    model = "74-Z",
                    manufacturer = "Aratech Repulsor Company",
                    costInCredits = "8000",
                    length = "3",
                    maxAtmospheringSpeed = "500",
                    crew = "1",
                    passengers = "1",
                    cargoCapacity = "4",
                    consumables = "1 day",
                    vehicleClass = "Speeder",
                    pilots = listOf(),
                    films = listOf(),
                    created = "",
                    edited = "",
                    url = "",
                ),
        )
    }
}
