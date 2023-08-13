package com.example.weather

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.widget.Toast
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
import com.example.weather.nav.Screen
import com.example.weather.placeSelection.CityBox
import com.example.weather.ui.theme.Typography
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener

@SuppressLint("MissingPermission")
@Composable
fun ProvinceSelection(
    fusedLocationClient: FusedLocationProviderClient,
    context: Context,
    navController: NavController
) {
    var currentLocation by remember {
        mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
    }

    fusedLocationClient.getCurrentLocation(
        LocationRequest.PRIORITY_HIGH_ACCURACY,
        object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                CancellationTokenSource().token

            override fun isCancellationRequested() = false
        }).addOnSuccessListener { location: Location? ->
        if (location == null)
            Toast.makeText(
                context, "Cannot get location.", Toast.LENGTH_SHORT
            ).show()
        else {
            currentLocation = LocationDetails(
                location.latitude, location.longitude
            )
            Toast.makeText(
                context, "Location detected.", Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Text(
            text = "Weather",
            style = Typography.h1,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 24.dp,
            )
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CityBox(
                City("My Location", currentLocation),
                navController = navController,
            )
            for (place in PROVINCES) {
                ProvinceBox(place, navController)
            }
        }
    }

}


@Composable
fun ProvinceBox(province: Province, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(120.dp)
            .padding(vertical = 12.dp)
            .clickable {
                navController.navigate(
                    Screen.CitySelection.withArgs(province.title)
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
                    text = province.title,
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
                val cityNames = province.cities.map { province.title }
                Text(
                    text = cityNames.joinToString(", ").take(40) + "...",
                )
            }
        }
    }
}
