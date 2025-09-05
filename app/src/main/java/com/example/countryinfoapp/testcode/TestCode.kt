package com.example.countryinfoapp.testcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle

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

@Composable
fun ConstraintWithBarrierExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        val (flag, countryName, officialName) = createRefs()

        // This barrier will take the END of the widest between flag and countryName
        val startBarrier = createEndBarrier(flag, countryName)

        // Flag Box (simulate a flag)
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.Red)
                .constrainAs(flag) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        // Country name (below the flag)
        Text(
            text = "USA",
            fontSize = 18.sp,
            modifier = Modifier.constrainAs(countryName) {
                top.linkTo(flag.bottom, margin = 8.dp)
                start.linkTo(flag.start) // align with flag
            }
        )

        // Official name should start AFTER the widest of (flag, India)
        Text(
            text = "United States Of America",
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(officialName) {
                top.linkTo(parent.top)
                start.linkTo(startBarrier, margin = 8.dp)
            }
        )
    }
}

@Composable
fun ChainLayoutExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (start, middle, end) = createRefs()

        // Create a horizontal chain
        createHorizontalChain(start, middle, end, chainStyle = ChainStyle.Packed)

        Text(
            text = "Start",
            modifier = Modifier.constrainAs(start) {
                top.linkTo(parent.top)
            }
        )

        Text(
            text = "Middle",
            modifier = Modifier.constrainAs(middle) {
                top.linkTo(parent.top)
            }
        )

        Text(
            text = "End",
            modifier = Modifier.constrainAs(end) {
                top.linkTo(parent.top)
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    var counter by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My App") })
        },
        bottomBar = {
            BottomAppBar {
                Text("Bottom Bar", modifier = Modifier.padding(16.dp))
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { counter++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
                .padding( 16.dp)        // ✅ This adds space from left and right
        ) {
            Text("You pressed the FAB $counter times")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TestComposablePreview() {
    MyScreen()
}
