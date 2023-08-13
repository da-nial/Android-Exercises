package com.example.booklist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

enum class LayoutType {
    LIST, GRID
}

fun getlayoutIcon(layoutType: LayoutType): ImageVector {
    if (layoutType == LayoutType.LIST)
        return Icons.Filled.List
    else
        return Icons.Filled.Menu
}