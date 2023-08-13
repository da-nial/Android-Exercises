package com.example.currencyconverter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.ui.theme.*

@Composable
fun MultiConvertScreen(conversionViewModel: ConversionViewModel = viewModel()) {
    val amountInUSD: Double by conversionViewModel.amountInUSD.observeAsState(0.0)

    MultiConvertSection(
        amountInUSD = amountInUSD,
        onAmountChange = { newValue, currency ->
            conversionViewModel.onAmountChange(
                newValue,
                currency
            )
        }
    )
}

@Composable
fun MultiConvertSection(
    amountInUSD: Double,
    onAmountChange: (String, Currency) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = ScreenBackgroundColor
            )
            .padding(
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (currency in CURRENCIES) {
            MultiConverteeCurrencySection(
                currency,
                amountInUSD,
                onAmountChange,
            )
        }
    }
}

@Composable
fun MultiConverteeCurrencySection(
    currency: Currency,
    amountInUSD: Double,
    onAmountChange: (String, Currency) -> Unit,
) {
    Row(
        modifier = Modifier
            .border(
                1.dp, color = DrawableColorFaded, shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = SectionBackgroundColor
            )
            .padding(
                vertical = 24.dp, horizontal = 16.dp
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .weight(2f),
                    value = convertFromUSD(amountInUSD, currency, true).toString(),
                    onValueChange = { newValue -> onAmountChange(newValue, currency) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle.Default.copy(
                        color = TextFieldColor,
                        background = SectionBackgroundColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = SectionBackgroundColor,
                        focusedIndicatorColor = Color.Transparent,
                    )
                )
                Row(modifier = Modifier.weight(1f)) {
                    MultiCurrencyButton(currency)
                }
            }
        }
    }
}

@Composable
fun MultiCurrencyButton(currency: Currency) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = currency.icon),
            contentDescription = "Currency Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )
        Text(
            text = currency.name,
            style = MaterialTheme.typography.h6,
            color = CurrencyColor,
        )
        Icon(
            Icons.Rounded.KeyboardArrowRight,
            contentDescription = "forward icon",
            tint = TextColor
        )
    }
}