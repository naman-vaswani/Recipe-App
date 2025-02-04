package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp (
    navController : NavHostController
)
{
    val recipeViewModel : MainViewModel = viewModel()
    val veiwstate by recipeViewModel.categorieState

    NavHost(
        navController = navController,
        startDestination = Screen.RecipeScreen.route
    )
    {
        composable(route  = Screen.RecipeScreen.route ){

            RecipeScreen(
//                    viewState =  veiwstate ,
                    navigateToDetail = {
                                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                                    navController.navigate(Screen.DetailScreen.route)
                    }
            )
        }

        composable(route = Screen.DetailScreen.route){

            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Meals>("cat") ?: Meals("","","","","","","","","","","","","","")

            CategoryDetailScreen(category = category)
        }
        
    }
    
    
    
}