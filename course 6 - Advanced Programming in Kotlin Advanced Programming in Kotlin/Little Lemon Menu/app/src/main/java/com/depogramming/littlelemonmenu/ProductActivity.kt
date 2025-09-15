package com.depogramming.littlelemonmenu

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class ProductActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("product", ProductItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("product")
        }
        val productItem = product ?: ProductItem("", 0.0, "", -1)

        setContent { ProductDetails(productItem) }
    }
}