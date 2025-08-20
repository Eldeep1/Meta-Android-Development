package com.depogramming.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    Column {
        TopAppBar(
            scope = scope,
        )
        UpperPanel()
        LowerPanel(navController, DishRepository.dishes)
    }
}
