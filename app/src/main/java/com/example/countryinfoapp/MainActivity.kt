package com.example.countryinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}


@Composable
fun MainScreen() {
    CountryInfoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            CountryCard()
        }
    }
}

@Composable
fun CountryCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .padding(10.dp)
            .wrapContentHeight(align = Alignment.Top)
            .border(1.dp, Color.LightGray),
        color = Color.Yellow,
        shadowElevation = 2.dp
    ) {
        Row()
        {
            Column(
                modifier = Modifier
                    .weight(0.2f)
            )
            {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(50.dp)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val imageResId = R.drawable.us
                    val imagePainter = painterResource(id = imageResId)
                    Image(
                        painter = imagePainter,
                        contentDescription = "Country Flag",
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = "USA",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 20.sp
                )
                Text(
                    text = "DC",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 15.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .weight(0.8f)
            ) {

                Text(
                    text = "United States of America",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth(fraction = 1.0f)
                )

                Text(
                    text = "North America",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth(fraction = 1.0f)
                )

                Text(
                    text = "South Asia",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth(fraction = 1.0f)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(fraction = 1.0f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(fraction = 0.1f)
                    )

                    Text(
                        text = "US Dollar",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(fraction = 0.4f)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(fraction = 0.3f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "+1",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(fraction = 1.0f)
                        )
                        Text(
                            text = ".in",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(fraction = 1.0f)
                        )
                    }


                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}
