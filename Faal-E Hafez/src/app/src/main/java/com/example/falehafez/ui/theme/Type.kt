package com.example.falehafez.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.falehafez.R


val main_fonts = FontFamily(
    Font(R.font.iran_nastaliq)
)
val secondary_fonts = FontFamily(
    Font(R.font.b_mitra)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = main_fonts,
        fontWeight = FontWeight.Thin,
        fontSize = 24.sp
    ),
    body2 = TextStyle(
        fontFamily = secondary_fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    h1 = TextStyle(
        fontFamily = main_fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    h6 = TextStyle(
        fontFamily = main_fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = main_fonts,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
//    h2 = TextStyle(
//        fontFamily = fonts,
//        fontWeight = FontWeight.Thin,
//        fontSize = 48.sp
//    )
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
