package com.example.booklist

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BooksOverviewScreen.route
    ) {
        composable(route = Screen.BooksOverviewScreen.route) {
            BooksOverview(navController = navController)
        }
        composable(
            route = Screen.BookDetailsScreen.route + "/{bookId}",
            arguments = listOf(navArgument("bookId") {
                type = NavType.IntType
            })
        ) { entry ->
            entry.arguments?.getInt("bookId")?.let {
                BookDetails(
                    bookId = it
                )
            }
        }
    }
}
