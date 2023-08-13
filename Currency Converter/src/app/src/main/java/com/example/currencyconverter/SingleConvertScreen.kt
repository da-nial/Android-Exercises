package com.example.currencyconverter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.currencyconverter.ui.theme.*


class ConversionViewModel : ViewModel() {
    private val _amountInUSD: MutableLiveData<Double> = MutableLiveData(0.0)
    val amountInUSD: LiveData<Double> = _amountInUSD

    fun onAmountChange(newAmount: String, newAmountCurrency: Currency) {
        _amountInUSD.value = convertToUSD(newAmount.toDouble(), newAmountCurrency)
    }
}

@Composable
fun SingleConvertScreen(
    initialCurrencyName: String,
    secondaryCurrencyName: String,
    conversionViewModel: ConversionViewModel = viewModel(),
    navController: NavController
) {
    val amountInUSD: Double by conversionViewModel.amountInUSD.observeAsState(0.0)
    ConvertSection(
        navController = navController,
        initialCurrencyName = initialCurrencyName,
        secondaryCurrencyName = secondaryCurrencyName,
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
fun ConvertSection(
    navController: NavController,
    initialCurrencyName: String,
    secondaryCurrencyName: String,
    amountInUSD: Double,
    onAmountChange: (String, Currency) -> Unit
) {
    var initialCurrency = CURRENCIES.find { it.name == initialCurrencyName }
    if (initialCurrency == null) {
        initialCurrency = CURRENCIES[0]
    }
    var secondaryCurrency = CURRENCIES.find { it.name == secondaryCurrencyName }
    if (secondaryCurrency == null) {
        secondaryCurrency = CURRENCIES[3]
    }

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
        ConverteeCurrencySection(
            initialCurrency,
            "You Pay",
            amountInUSD,
            onAmountChange,
            navController
        )
        Image(
            painter = painterResource(id = R.drawable.ic_swap_vertical),
            contentDescription = "swap vertical icon",
            modifier = Modifier
                .border(1.dp, DrawableColorFaded, CircleShape)
                .padding(12.dp)
                .size(36.dp)
                .clip(CircleShape),
            colorFilter = ColorFilter.tint(color = DrawableColor)
        )
        ConverteeCurrencySection(
            secondaryCurrency,
            "You Get",
            amountInUSD,
            onAmountChange,
            navController
        )
        ExchangeRateSection(
            initialCurrency, secondaryCurrency
        )
    }
}

@Composable
fun ConverteeCurrencySection(
    currency: Currency,
    title: String,
    amountInUSD: Double,
    onAmountChange: (String, Currency) -> Unit,
    navController: NavController
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
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                color = TextColor,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
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
                    CurrencyButton(currency, navController)
                }
            }
        }
    }
}


@Composable
fun ExchangeRateSection(initialCurrency: Currency, secondaryCurrency: Currency) {
    val exchangeRate = getExchangeRate(initialCurrency, secondaryCurrency)
    Text(
        text = "1 ${initialCurrency.name} ≈ $exchangeRate ${secondaryCurrency.name}",
        color = TextColor,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}

@Composable
fun CurrencyButton(currency: Currency, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
            .clickable(onClick = { navController.navigate(Screen.InitialCurrencyScreen.route) }),
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