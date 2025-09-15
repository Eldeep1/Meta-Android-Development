package com.depogramming.littlelemonmenu

class FilterHelper {//TODO create a FilterHelperTest and write a unit test for filterProducts

    fun filterProducts(type: FilterType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            FilterType.All -> productsList
            FilterType.Dessert -> productsList.filter { it.category=="Dessert" }
            FilterType.Drinks ->productsList.filter { productItem -> productItem.category=="Drinks" }
            FilterType.Food -> productsList.filter { it.category=="Food" }
        }
    }

}