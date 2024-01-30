package com.utick.dvtcodingassessment.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.utick.dvtcodingassessment.ui.util.amentFamily

@Composable
fun DisplayAlert(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    confirmButtonText: String,
    dismissButtonText: String
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle, fontFamily = amentFamily)
        },
        text = {
            Text(text = dialogText, fontFamily = amentFamily)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmButtonText, fontFamily = amentFamily)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissButtonText, fontFamily = amentFamily)
            }
        }
    )
}