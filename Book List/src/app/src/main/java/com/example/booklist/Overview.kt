package com.example.booklist

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController

@Composable
fun BooksOverview(navController: NavController) {
    var layout by remember { mutableStateOf(LayoutType.LIST) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Books Overview") },
                navigationIcon = if (navController.previousBackStackEntry == null) {
                    {
                        IconButton(
                            onClick = {
                                layout = if (layout == LayoutType.LIST)
                                    LayoutType.GRID
                                else
                                    LayoutType.LIST
                            }
                        ) {
                            Icon(
                                imageVector = getlayoutIcon(layout),
                                contentDescription = "Layout icon"
                            )
                        }
                    }
                } else {
                    null
                }

            )
        },
    ) {
        if (layout == LayoutType.LIST) {
            BookList(navController)
        } else {
            BookGrid(navController)
        }
    }
}
