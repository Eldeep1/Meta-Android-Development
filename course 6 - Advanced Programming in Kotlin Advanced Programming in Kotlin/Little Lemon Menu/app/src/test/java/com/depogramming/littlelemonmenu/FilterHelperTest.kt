package com.depogramming.littlelemonmenu

import org.junit.Assert.assertEquals
import org.junit.Test

class FilterHelperTest {

    @Test
    fun filterProducts_filterTypeDessert_croissantReturned() {
        val sampleProductsList = mutableListOf(
            ProductItem(title = "Black tea", price = 3.00, category = "Drinks", image = 0),
            ProductItem(title = "Croissant", price = 7.00, category = "Dessert", image = 0),
            ProductItem(title = "Bouillabaisse", price = 20.00, category = "Food", image = 0)
        )
        val filterHelper = FilterHelper()
        val expectedResult = listOf(sampleProductsList[1]) // A list containing only the croissant


        val result = filterHelper.filterProducts(
            type = FilterType.Dessert,
            productsList = sampleProductsList
        )

        assertEquals(expectedResult, result)
    }
}