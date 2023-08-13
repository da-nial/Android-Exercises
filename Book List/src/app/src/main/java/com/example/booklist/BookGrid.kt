package com.example.booklist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookGrid(navController: NavController) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 120.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        itemsIndexed(books) { index, book ->
            BookGridItem(book = book, navController)
        }
    }
}

@Composable
fun BookGridItem(book: Book, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = book.imageId),
            contentDescription = "Photo of this book",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(5))
                .clickable {
                    navController.navigate(
                        Screen.BookDetailsScreen.withArgs(book.id.toString())
                    )
                }
        )
        Text(
            text = book.name,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = book.author,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(12.dp)
        )
    }
}