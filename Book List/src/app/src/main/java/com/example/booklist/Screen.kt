package com.example.booklist


sealed class Screen(val route: String) {
    object BooksOverviewScreen : Screen("books_overview")
    object BookDetailsScreen : Screen("book_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
