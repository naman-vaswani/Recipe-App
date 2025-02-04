package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meals(
    // Propertiess of all Categories available in Json file that we will get from meal-database api
    val strMeal : String,
    val strCategory: String,
    val strMealThumb : String,
    val strInstructions: String,
    val strIngredient1 : String,
    val strIngredient2 : String,
    val strIngredient3 : String,
    val strIngredient4 : String,
    val strIngredient5 : String,
    val strIngredient6 : String,
    val strIngredient7 : String,
    val strIngredient8 : String,
    val strIngredient9 : String,
    val strIngredient10 : String
) : Parcelable

data class MealsResponse(
    val meals: List<Meals> // "categories" is taken from the json file which contains a
                                    // list of "categories"
)
