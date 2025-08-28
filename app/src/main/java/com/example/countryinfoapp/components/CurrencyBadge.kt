package com.example.countryinfoapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max

@Composable
fun CurrencyBadge(text: String = "$") {
    var textSize by remember { mutableStateOf(IntSize.Companion.Zero) }

    Box(
        modifier = Modifier.Companion
            .padding(2.dp)
            .onSizeChanged { textSize = it }
            .drawBehind {
                val radius = max(
                    textSize.width.toFloat(),
                    textSize.height.toFloat()
                ) / 2f + 8f // padding around
                drawCircle(
                    color = Color.Companion.LightGray,
                    radius = radius,
                    center = Offset(this.size.width / 2f, this.size.height / 2f)
                )
            },
        contentAlignment = Alignment.Companion.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Companion.Center,
        )
    }
}