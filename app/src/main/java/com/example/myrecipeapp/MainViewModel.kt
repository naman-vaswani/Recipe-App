package com.example.myrecipeapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    data class RecipeState(
        val loading : Boolean = true,
        val list : List<Meals> = emptyList(),
        val error : String? = null
    )

    private val _categorieState = mutableStateOf(RecipeState())

    val categorieState : State<RecipeState> = _categorieState



    init {
        fetchCategories(query = "")
    }
     fun fetchCategories(query : String){
        // viewModel = coroutine
        viewModelScope.launch {
            try {
                Log.d("MainViewModel", "fetchMeals() called with query: $query")
                val response = recipeService.getCategories(query)
                _categorieState.value = _categorieState.value.copy(
                    list = response.meals,
                    loading = false,
                    error = null
                )

            }catch (e : Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }

    }
}