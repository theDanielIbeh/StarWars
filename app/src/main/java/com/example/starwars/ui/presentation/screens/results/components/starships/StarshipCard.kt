package com.example.starwars.ui.presentation.screens.results.components.starships

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
import com.example.starwars.domain.model.starships.StarShipsItem

@Composable
fun StarshipCard(
    starship: StarShipsItem,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = starship.name ?: "Untitled",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(8.dp))

            InfoText("Model", starship.model ?: "Unknown")
            InfoText("Manufacturer", starship.manufacturer ?: "Unknown")
            InfoText("Cost in credits", starship.costInCredits ?: "Unknown")
            InfoText("Length", starship.length ?: "Unknown")
            InfoText("Crew", starship.crew ?: "Unknown")
            InfoText("Passengers", starship.passengers ?: "Unknown")
            InfoText("Cargo Capacity", starship.cargoCapacity ?: "Unknown")
        }
    }
}

@Composable
fun InfoText(
    label: String,
    value: String,
) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StarshipCardPreview() {
    val sampleStarship =
        StarShipsItem(
            name = "Millennium Falcon",
            model = "YT-1300 light freighter",
            manufacturer = "Corellian Engineering Corporation",
            costInCredits = "100000",
            length = "34.37",
            maxAtmospheringSpeed = "1050",
            crew = "4",
            passengers = "6",
            cargoCapacity = "100000",
            consumables = "2 months",
            hyperdriveRating = "0.5",
            mGLT = "75",
            starshipClass = "Light freighter",
            pilots = emptyList(),
            films = emptyList(),
            created = "",
            edited = "",
            url = "",
        )

    MaterialTheme {
        StarshipCard(starship = sampleStarship)
    }
}
