package com.example.countryinfoapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestComposable() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(0.2f)
                .background(Color.LightGray)
        ) {
            Text("One")
            Text("Two")
            Text("Three")
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .background(Color.Cyan)
        ) {
            Text("Four")

        }
    }

}

@Preview(showBackground = true)
@Composable
fun TestComposablePreview() {
    TestComposable()
}
