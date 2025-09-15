package com.depogramming.littlelemonmenu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.depogramming.littlelemonmenu.ui.theme.LittleLemonMenuTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class MainActivity : ComponentActivity() {



    private val productsState: MutableStateFlow<Products> =
        MutableStateFlow(Products(ProductsWarehouse.productsList))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LittleLemonMenuTheme { InitUI() } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun InitUI() {
        val context = LocalContext.current
        var menuExpanded by remember { mutableStateOf(false) }

        val products by productsState.collectAsState()
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue, // Set the background color here
                        titleContentColor = Color.White, // Set the title color for good contrast
                    ),
                    title = { Text(stringResource(id = R.string.app_name)) },
                    actions = {
                        IconButton(onClick = { menuExpanded  = true }, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }

                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.sort_a_z)) },
                                onClick = {
                                    sortProducts(SortType.Alphabetically)
                                    menuExpanded = false // Close the menu
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.sort_price_ascending)) },
                                onClick = {
                                    sortProducts(SortType.PriceAsc)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.sort_price_descending)) },
                                onClick = {
                                    sortProducts(SortType.PriceDesc)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.filter_all)) },
                                onClick = {
                                    filterProducts(FilterType.All)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.filter_drinks)) },
                                onClick = {
                                    filterProducts(FilterType.Drinks)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.filter_food)) },
                                onClick = {
                                    filterProducts(FilterType.Food)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.filter_dessert)) },
                                onClick = {
                                    filterProducts(FilterType.Dessert)
                                    menuExpanded = false
                                }
                            )
                        }
                    }
                )
            }
        ) {paddingValues ->
            ProductsGrid(
                contentPadding = paddingValues,
                products = products,
                onProductClick = { productItem ->
                    startProductActivity(productItem, context)
                }
            )
        }
    }

    private fun filterProducts(type: FilterType) {
        val filteredList = FilterHelper().filterProducts(type, ProductsWarehouse.productsList)
        productsState.update { Products(filteredList) }
    }

    private fun sortProducts(alphabetically: SortType) {

    }

    private fun startProductActivity(productItem: ProductItem,context: Context) {

        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("product", productItem)
        context.startActivity(intent)
    }

}