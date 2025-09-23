package com.example.countryinfoapp.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun MyNewAlertDialog(
    showDialog: MutableState<Boolean>,
    title: String,
    message: String,
    currentCapital: String,
    positiveAction: (String) -> Unit
) {
    var newCapital by remember { mutableStateOf("") }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text(text = title, fontSize = 30.sp)
            },
            text = {
                Column {
                    Text(message, fontSize = 25.sp)
                    Text(text = "Current Country Capital: $currentCapital", fontSize = 20.sp)
                    OutlinedTextField(
                        value = newCapital,
                        onValueChange = { newCapital = it },
                        label = { Text(text = "New Capital") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        positiveAction(newCapital)
                        showDialog.value = false
                    }
                ) {
                    Text(text = "Confirm")
                }
            }
        )
    }
}
