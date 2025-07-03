package com.example.starwars.ui.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * A reusable composable that renders the core UI of the Home screen,
 * including a text input and submit button.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param text The current input text value.
 * @param onTextChange Callback for updating the input text.
 * @param onSubmitClick Callback when the user clicks the submit button.
 */
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onSubmitClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter a category (vehicles, starships, films)", style = MaterialTheme.typography.titleMedium)

        TextField(
            singleLine = true,
            value = text,
            onValueChange = onTextChange,
            label = { Text("Enter category") },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(
                        onClick = { onTextChange("") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear text",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSubmitClick()
                }
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            onClick = onSubmitClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("SEARCH")
        }
    }
}