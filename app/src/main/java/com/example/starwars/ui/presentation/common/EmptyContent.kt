package com.example.starwars.ui.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwars.R

/**
 * A composable that displays a centered message and sad icon
 * when no content is available (e.g., empty list).
 */
@Composable
fun EmptyContent() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = stringResource(id = R.string.sad_face_icon),
            tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
        )
        Text(
            text = stringResource(id = R.string.empty_content),
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
        )
    }
}

/**
 * Preview of [EmptyContent] for visual inspection in Android Studio.
 */
@Composable
@Preview
private fun EmptyContentPreview() {
    EmptyContent()
}
