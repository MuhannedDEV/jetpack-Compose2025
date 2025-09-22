package com.example.countryinfoapp.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun MyAlertDialog(
    showDialog: MutableState<Boolean>,
    title: String,
    message: String,
    positiveAction: () -> Unit,
    dismissAction: (() -> Unit)? = null
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
                dismissAction?.invoke()
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(message)
            },
            confirmButton = {
                Button(onClick = {
                    positiveAction()
                    showDialog.value = false
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog.value = false
                    dismissAction?.invoke()
                }) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}
