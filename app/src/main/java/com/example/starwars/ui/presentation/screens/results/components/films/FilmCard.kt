package com.example.starwars.ui.presentation.screens.results.components.films

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
import com.example.starwars.domain.model.film.FilmItem

@Composable
fun FilmCard(
    film: FilmItem,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title
            Text(
                text = film.title ?: "Untitled",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.Companion.height(12.dp))

            // Details
            FilmInfo("Episode", film.episodeId?.toString() ?: "N/A")
            FilmInfo("Director", film.director ?: "Unknown")
            FilmInfo("Producer", film.producer ?: "Unknown")
            FilmInfo("Release Date", film.releaseDate ?: "Unknown")

            Spacer(modifier = Modifier.height(12.dp))

            // Opening Crawl
            Text(
                text = "Opening Crawl:",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
            )
            Text(
                text = film.openingCrawl?.take(120)?.plus("...") ?: "Not available",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}

@Composable
private fun FilmInfo(
    label: String,
    value: String,
) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Companion.Bold,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilmCardPreview() {
    MaterialTheme {
        FilmCard(
            film =
                FilmItem(
                    title = "A New Hope",
                    episodeId = 4,
                    openingCrawl = "It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory...",
                    director = "George Lucas",
                    producer = "Gary Kurtz, Rick McCallum",
                    releaseDate = "1977-05-25",
                    characters = listOf(),
                    planets = listOf(),
                    starships = listOf(),
                    vehicles = listOf(),
                    species = listOf(),
                    created = "",
                    edited = "",
                    url = "",
                ),
        )
    }
}
