package com.example.weather.placeSelection

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.City
import com.example.weather.Province
import com.example.weather.R
import com.example.weather.nav.Screen
import com.example.weather.ui.theme.Typography

@Composable
fun CitySelection(
    province: Province,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (city in province.cities) {
            CityBox(city, navController)
        }
    }

}

@Composable
fun CityBox(city: City, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(120.dp)
            .padding(vertical = 12.dp)
            .clickable {
                navController.navigate(
                    Screen.PlaceWeather.withArgs(
                        city.details.latitude.toString(),
                        city.details.longitude.toString()
                    )
                )
            },
        contentAlignment = Alignment.TopCenter,
    ) {
        Image(
            painter = painterResource(id = R.drawable.breezy),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
        )
        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = city.title,
                    style = Typography.h3
                )
                Text(
                    text = "2°",
                    style = Typography.h1
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Haze",
                )
                Text(
                    text = "H:7°  L:3°",
                )
            }
        }
    }
}
