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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.countryinfoapp.R
import com.example.countryinfoapp.components.CurrencyBadge
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

            Image(
                painter = imagePainter,
                contentDescription = "Country Flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(flagImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
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

            Text(
                text = countryInfo.subRegion,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(subRegionText) {
                        top.linkTo(regionText.bottom, margin = 2.dp)
                        start.linkTo(regionText.start)
                        end.linkTo(regionText.end)
                    }
                    .fillMaxWidth(0.75f) // Adjust fraction as needed
            )

            CurrencyBadge(
                text = countryInfo.currencySymbol,
                modifier = Modifier.constrainAs(currencySymbolBadge) {
                    top.linkTo(subRegionText.bottom, margin = 8.dp)
                    start.linkTo(subRegionText.start)
//                    start.linkTo(flagImage.end, margin = 8.dp)
//                    bottom.linkTo(parent.bottom, margin = 8.dp)
                }
            )

            Text(
                text = countryInfo.currencyName,
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .constrainAs(currencyNameText) {
                        top.linkTo(currencySymbolBadge.top)
                        bottom.linkTo(currencySymbolBadge.bottom)
                        start.linkTo(currencySymbolBadge.end, margin = 4.dp)
                        end.linkTo(mobileCodeText.start, margin = 4.dp)
                    }
            )

            Text(
                text = countryInfo.mobileCode,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.constrainAs(mobileCodeText) {
                    top.linkTo(currencySymbolBadge.top)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = countryInfo.tld,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.constrainAs(tldText) {
                    top.linkTo(mobileCodeText.bottom)
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
