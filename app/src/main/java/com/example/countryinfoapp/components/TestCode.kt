package com.example.countryinfoapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp

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

@Composable
fun TestConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        val (name, email, office, location) = createRefs()

        Text("Name", Modifier.constrainAs(name) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        Text("Email", Modifier.constrainAs(email) {
            top.linkTo(name.bottom)
            start.linkTo(parent.start)
        })

        Text("Office", Modifier.constrainAs(office) {
            top.linkTo(parent.top)
            start.linkTo(name.end, margin = 16.dp)
        })

        Text("Location", Modifier.constrainAs(location) {
            top.linkTo(office.bottom)
            start.linkTo(name.end, margin = 16.dp)
        })
    }

    }

@Composable
fun ConstraintWithGuidelinesExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 🔹 Create a vertical guideline at 40% from start
        val startGuideline = createGuidelineFromStart(0.4f)

        // 🔸 Create references for each text
        val (name, email, office, location) = createRefs()

        Text("Name", Modifier.constrainAs(name) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        Text("Email", Modifier.constrainAs(email) {
            top.linkTo(name.bottom, margin = 8.dp)
            start.linkTo(parent.start)
        })

        Text("Office", Modifier.constrainAs(office) {
            top.linkTo(parent.top)
            start.linkTo(startGuideline) // 🟢 aligns to guideline
        })

        Text("Location", Modifier.constrainAs(location) {
            top.linkTo(office.bottom, margin = 8.dp)
            start.linkTo(startGuideline) // 🟢 aligns to guideline
        })
    }
}


    @Preview(showBackground = true)
    @Composable
    fun TestComposablePreview() {
        ConstraintWithGuidelinesExample()
    }
