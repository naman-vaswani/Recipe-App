package com.example.myrecipeapp

import android.graphics.Paint.Align
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RecipeScreen(
    modifier : Modifier = Modifier,
  //  viewState : MainViewModel.RecipeState,
    recipeViewModel: MainViewModel = viewModel(),
    navigateToDetail : (Meals) -> Unit
)
{

    val viewState by recipeViewModel.categorieState // Most important thing, its observing categoieState
   // val recipeViewModel : MainViewModel = viewModel()  // recipeViewModel inherits property of MainViewModel
                                                        // viewModel() is a class

    val keyboardController = LocalSoftwareKeyboardController.current
    var mealName by remember{ mutableStateOf("") }
    



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        TopAppBar(
            title = {
                Text(
                    text = "Find Your Recipe",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(8.dp)
                .height(32.dp),
            actions = {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(28.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = mealName,
            onValueChange = {
                mealName = it
                recipeViewModel.fetchCategories(it)
            },
            enabled = true,
            placeholder = { Text("Enter dish name", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                recipeViewModel.fetchCategories(mealName)
            keyboardController?.hide()
            },
            modifier = Modifier.padding(8.dp))
        {
            Text("Search for dish")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxSize()) {

            when {
                viewState.loading -> {
                    CircularProgressIndicator(modifier.align(Alignment.Center))
                }


                viewState.error != null -> {
                    Text("No dish found names as : $mealName !!!",
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.TopCenter)
                        )

                }

                else -> {
                    CategoryScreen(categories = viewState.list, navigateToDetail)

                }
            }
        }
    }
}



@Composable
fun CategoryScreen(categories  : List<Meals>,
                   navigateToDetail : (Meals) -> Unit
                )
{


    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)

    ){
        items(categories){
            Category ->
            CategoryItem(category = Category , navigateToDetail )


        }
    }

}


@Composable
fun CategoryItem(category: Meals , navigateToDetail : (Meals) -> Unit){ // Category contains link to the api
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navigateToDetail(category) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(category.strMealThumb),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = category.strMeal,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}