package com.example.falehafez

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController


@Composable
fun MainScreen(navController: NavController) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hafez),
                    contentDescription = stringResource(id = R.string.main_image_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .weight(3f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "فال حافـــظ",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "گنجینه‌ای با بیش از ۵ شعر",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.75f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.PeomScreen.route)
                    }, shape = RoundedCornerShape(15),
                    modifier = Modifier.weight(3f)
                ) {
                    Text(text = "یا بخت و یا اقبال", color = Color.White)
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.AboutScreen.route)
                    },
                    shape = CutCornerShape(10),
                    modifier = Modifier.weight(2f)
                ) {
                    Text(text = "دربارهٔ فال حافظ", color = Color.White)
                }
            }
        }
    }
}
