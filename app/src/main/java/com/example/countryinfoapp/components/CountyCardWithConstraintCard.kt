package com.example.countryinfoapp.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.countryinfoapp.R
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.data.CountryInfo
import com.example.countryinfoapp.data.Currency // Added import
import com.example.countryinfoapp.data.Flags    // Added import
import com.example.countryinfoapp.data.Idd      // Added import
import com.example.countryinfoapp.data.Name     // Added import
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme // Added import
import com.example.countryinfoapp.viewmodel.CountryViewModel

@Composable
fun CountryCardWithConstraintLayout(
    countryInfo: Country,
    showDeleteAlertDialog: MutableState<Boolean>,
    showUpdateAlertDialog: MutableState<Boolean>,
    viewModel: CountryViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        Log.d("CountryCard", "Double tapped on ${countryInfo.name?.common}")
                        viewModel.selectedCountryForUpdation.value = countryInfo
                        showUpdateAlertDialog.value = true
                    },
                    onLongPress = {
                        Log.d("CountryCard", "Long pressed on ${countryInfo.name?.common}")
                        showDeleteAlertDialog.value = true
                        viewModel.selectedCountryForDeletion.value = countryInfo
                    }
                )
            }
    ) {
        val (flagImage, commonName, officialName, capitalText, regionText, subRegionText, currencySymbolBadge, currencyNameText, mobileCodeText, tldText) = createRefs()

        countryInfo?.let {
            AsyncImage(
                model = it.flags?.png, // This will now use the android.resource URI for local drawables
                contentDescription = it?.flag,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(flagImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .width(100.dp)
                    .height(50.dp)
            )
        }

        countryInfo.name?.common?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.constrainAs(commonName) {
                    top.linkTo(flagImage.bottom, margin = 4.dp)
                    start.linkTo(flagImage.start)
                    end.linkTo(flagImage.end)
                    width = Dimension.fillToConstraints // ✅ This is the key fix
                }
            )
        }

        countryInfo.capital?.getOrNull(0)?.let { // Used getOrNull for safety
            Text(
                text = it,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,

                modifier = Modifier.constrainAs(officialName) {
                    top.linkTo(commonName.bottom, margin = 2.dp)
                    start.linkTo(commonName.start)
                    end.linkTo(commonName.end)
                    width = Dimension.fillToConstraints
                }
            )
        }

        countryInfo.name?.official?.let {
            Text(
                text = it,
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
        }

        countryInfo?.region?.let {
            Text(
                text = it,
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
        }

        countryInfo?.subregion?.let {
            Text(
                text = it,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(subRegionText) {
                        top.linkTo(regionText.bottom, margin = 4.dp)
                        start.linkTo(regionText.start)
                        end.linkTo(regionText.end)
                    }
            )
        }

        countryInfo?.currencies?.entries?.firstOrNull()?.let {
            val leftColumnEnd = createEndBarrier(flagImage, commonName, officialName)
            CurrencyBadge( // Assuming CurrencyBadge is an existing composable
                text = it.value.symbol ?: "",
                modifier = Modifier
                    .constrainAs(currencySymbolBadge) {
                        baseline.linkTo(currencyNameText.baseline)
                        start.linkTo(leftColumnEnd, margin = 50.dp)
                    }
            )
        }

        countryInfo?.currencies?.entries?.firstOrNull()?.let {
            Text(
                text = it.value.name ?: "",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(currencyNameText) {
                        top.linkTo(subRegionText.bottom, margin = 4.dp)
                        start.linkTo(subRegionText.start)
                        end.linkTo(subRegionText.end)
                    }
            )
        }
        countryInfo.idd?.let {
            val suffix = it.suffixes?.getOrNull(0) ?: ""
            Text(
                text = (it.root ?: "") + suffix,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.constrainAs(mobileCodeText) {
                    baseline.linkTo(currencyNameText.baseline)
                    end.linkTo(parent.end)
                }
            )
        }
        countryInfo.tld?.getOrNull(0)?.let {
            Text(
                text = it,
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

//@Preview(showBackground = true, name = "Country Card Detailed Preview USA")
//@Composable
//fun CountryCardWithConstraintLayoutDetailedPreview() {
//    CountryInfoAppTheme {
//        CountryCardWithConstraintLayout(
//            countryInfo = Country(
//                name = Name(common = "United States", official = "United States of America"),
//                capital = listOf("Washington, D.C."),
//                region = "Americas",
//                subregion = "North America",
//                flags = Flags(
//                    png = "android.resource://com.example.countryinfoapp/drawable/us",
//                    svg = null // Explicitly set svg to null
//                ),
//                flag = "Flag of the United States",
//                currencies = mapOf("USD" to Currency(name = "United States Dollar", symbol = "$")),
//                idd = Idd(root = "+1", suffixes = listOf()),
//                tld = listOf(".us")
//            )
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun CountryCardWithConstraintLayoutPreview() {
//    CountryCardWithConstraintLayout(
//        countryInfo = CountryInfo(
//            R.drawable.us, // Assuming you have a drawable named 'us'
//            "United States of America",
//            "United States ",
//            "D.C",
//            "North America",
//            "America",
//            "$",
//            "US.Dollar",
//            "+1",
//            ".us"
//        )
//    )
//}
