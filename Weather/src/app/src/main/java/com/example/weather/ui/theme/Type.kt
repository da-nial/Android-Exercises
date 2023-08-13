package com.example.weather.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weather.R

val SFProDisplayBold = FontFamily(
    Font(R.font.sf_pro_display_bold)
)
val SFProDisplaySemiBold = FontFamily(
    Font(R.font.sf_pro_display_semibold)
)

val SFProDisplayRegular = FontFamily(
    Font(R.font.sf_pro_display_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = SFProDisplaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        color = Color.White,
    ),
    h2 = TextStyle(
        fontFamily = SFProDisplayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        color = Color.White
    ),
    h3 = TextStyle(
        fontFamily = SFProDisplaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        color = Color.White
    ),
    h4 = TextStyle(
        fontFamily = SFProDisplaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        color = Color.White
    ),
    h5 = TextStyle(
        fontFamily = SFProDisplaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        color = Color.White
    ),
    h6 = TextStyle(
        fontFamily = SFProDisplayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
        color = Color.White
    ),
    body1 = TextStyle(
        fontFamily = SFProDisplayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    body2 = TextStyle(
        fontFamily = SFProDisplaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)