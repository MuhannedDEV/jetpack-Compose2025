package com.example.countryinfoapp.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.countryinfoapp.R
import com.example.countryinfoapp.data.CountryInfo

@Composable
fun CountryCardWithConstraintLayout(countryInfo: CountryInfo) {
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
            val (flagImage, commonName, officialName, capitalText, regionText, subRegionText, currencySymbolBadge, currencyNameText, mobileCodeText, tldText) = createRefs()

            val imageResId = countryInfo.flagId
            val imagePainter = painterResource(id = imageResId)

            AsyncImage(
                model = countryInfo.flagId,
                contentDescription = "Country Flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(flagImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .width(100.dp)
                    .height(50.dp)
            )

            Text(
                text = countryInfo.commonName,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(commonName) {
                    top.linkTo(flagImage.bottom, margin = 4.dp)
                    start.linkTo(flagImage.start)
                    end.linkTo(flagImage.end)
                    width = Dimension.fillToConstraints // ✅ This is the key fix
                }
            )

            Text(
                text = countryInfo.officialName,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                modifier = Modifier.constrainAs(officialName) {
                    top.linkTo(commonName.bottom, margin = 2.dp)
                    start.linkTo(commonName.start)
                    end.linkTo(commonName.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = countryInfo.nationalCapital,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(capitalText) {
                        top.linkTo(parent.top)
                        start.linkTo(flagImage.end, margin = 8.dp)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(0.75f) // Adjust fraction as needed
            )

            Text(
                text = countryInfo.region,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(regionText) {
                        top.linkTo(capitalText.bottom, margin = 2.dp)
                        start.linkTo(capitalText.start)
                        end.linkTo(capitalText.end)
                    }
                    .fillMaxWidth(0.75f) // Adjust fraction as needed
            )

// America
            Text(
                text = countryInfo.subRegion,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(subRegionText) {
                        top.linkTo(regionText.bottom, margin = 4.dp)
                        start.linkTo(regionText.start)
                        end.linkTo(regionText.end)
                    }
            )

            val leftColumnEnd = createEndBarrier(flagImage, commonName, officialName)
            CurrencyBadge(
                text = countryInfo.currencySymbol,
                modifier = Modifier
                    .constrainAs(currencySymbolBadge) {
                        baseline.linkTo(currencyNameText.baseline)
                        start.linkTo(leftColumnEnd, margin = 50.dp)
                }
            )

// US Dollar
            Text(
                text = countryInfo.currencyName,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(currencyNameText) {
                        top.linkTo(subRegionText.bottom, margin = 4.dp)
                        start.linkTo(subRegionText.start) // match subRegion
                        end.linkTo(subRegionText.end)
                    }
            )

            Text(
                text = countryInfo.mobileCode,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.constrainAs(mobileCodeText) {
                    baseline.linkTo(currencyNameText.baseline)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = countryInfo.tld,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.constrainAs(tldText) {
                    top.linkTo(mobileCodeText.bottom,)
                    end.linkTo(parent.end)
                }
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryCardWithConstraintLayoutPreview() {
    CountryCardWithConstraintLayout(
        countryInfo = CountryInfo(
            R.drawable.us, // Assuming you have a drawable named 'us'
            "United States of America",
            "United States ",
            "D.C",
            "North America",
            "America",
            "$",
            "US.Dollar",
            "+1",
            ".us"
        )
    )
}
