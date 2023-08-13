package com.example.currencyconverter

import androidx.annotation.DrawableRes
import kotlin.math.pow
import kotlin.math.roundToInt


data class Currency(
    val name: String,
    @DrawableRes val icon: Int,
    val toUSDExchangeRate: Double,
)

val CURRENCIES = listOf(
    Currency("IRR", R.drawable.irr, 0.0000024),
    Currency("USD", R.drawable.usd, 1.0),
    Currency("CAD", R.drawable.cad, 0.735567),
    Currency("GBP", R.drawable.gbp, 1.20314),
    Currency("EUR", R.drawable.eur, 1.062135),
    Currency("AED", R.drawable.aed, 0.272479)
)


fun convertFromUSD(amountInUSD: Double, toCurrency: Currency, doRound: Boolean = false): Double {
    val amountInCurrency = amountInUSD * (1.0 / toCurrency.toUSDExchangeRate)
    if (doRound) {
        return round(amountInCurrency, 2)
    } else {
        return amountInCurrency
    }
}

fun convertToUSD(amount: Double, currency: Currency): Double {
    return amount * currency.toUSDExchangeRate
}

fun getExchangeRate(initialCurrency: Currency, secondaryCurrency: Currency): Double {
    return convertFromUSD(convertToUSD(1.0, initialCurrency), secondaryCurrency)
}

fun round(value: Double, numDecimals: Int): Double {
    val factor = 10.0.pow(numDecimals)
    return (value * factor).roundToInt() / factor
}