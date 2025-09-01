package com.example.countryinfoapp.testcode

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.countryinfoapp.R
import com.example.countryinfoapp.data.CountryInfo

@Composable
fun Guidelines(countryInfo: CountryInfo) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .padding(10.dp)
            .wrapContentHeight(align = Alignment.Top)
            .border(1.dp, Color.LightGray),
        shadowElevation = 2.dp
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            val (flagImage, commonName, officialName) = createRefs()
            val imageResId = countryInfo.flagId
            val imagePainter = painterResource(id = imageResId)

            var topGuideline = createGuidelineFromTop(2.dp)
            var startGuideline = createGuidelineFromStart(2.dp)



            Image(
                painter = imagePainter,
                contentDescription = "Country Flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(flagImage) {
                        top.linkTo(topGuideline)
                        start.linkTo(startGuideline)
                    }
                    .width(80.dp)
                    .height(50.dp)
            )

            Text(
                text = countryInfo.commonName,
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(commonName) {
                    top.linkTo(flagImage.bottom, margin = 4.dp)
                    start.linkTo(flagImage.start)
                    end.linkTo(flagImage.end)
                }
            )

            Text(
                text = countryInfo.officialName,
                fontSize = 15.sp,
                modifier = Modifier.constrainAs(officialName) {
                    top.linkTo(commonName.bottom, margin = 2.dp)
                    start.linkTo(commonName.start)
                    end.linkTo(commonName.end)
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuidelinesLayout() {
    Guidelines(
        countryInfo = CountryInfo(
            R.drawable.us, // Assuming you have a drawable named 'us'
            "U.S.A",
            "United States of America",
            "D.C",
            "North America",
            "America",
            "$",
            "US. Dollar Dollar",
            "+1",
            ".us"
        )
    )
}
