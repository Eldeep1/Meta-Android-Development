package com.depogramming.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Column {
        TopAppBar(
            scope = scope,
            drawerState = drawerState
        )
        UpperPanel()
        LowerPanel(navController, DishRepository.dishes)
    }
}
