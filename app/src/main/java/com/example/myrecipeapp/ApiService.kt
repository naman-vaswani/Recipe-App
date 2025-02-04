package com.example.myrecipeapp


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val _retrofit =
     Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/") // Make sure the baseUrl is with https
     .addConverterFactory(GsonConverterFactory.create())
     .build()


interface ApiService{

    @GET("search.php?s=")
    suspend fun getCategories(@Query("s") s : String) : MealsResponse // CattegoriesResponnse contains list of categories in Category.kt

    // For Calling a website we need to use "@GET" keyword
    // Allows us to do an Http request. we need to tell it
    //where do this http request should go to in ()
}

 val recipeService = _retrofit.create(ApiService :: class.java)
