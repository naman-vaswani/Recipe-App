package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CategoryDetailScreen(category: Meals) {
    var selectedTab by remember { mutableStateOf(0) } // 0 for Recipe, 1 for Ingredients

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Dish Image
        Image(
            painter = rememberAsyncImagePainter(category.strMealThumb),
            contentDescription = "${category.strMeal} Thumbnail",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f) // 16:9 aspect ratio
                .clip(RoundedCornerShape(12.dp))
        )

        // Dish Title
        Text(
            text = category.strMeal,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 28.sp, // Increased font size
            fontStyle = FontStyle.Italic,
            color = Color.White, // White text for contrast
            modifier = Modifier
                .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                .background(
                    color = Color(0xFF6D4C41), // Eye-catching Purple Background
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp) // Inner padding
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tab Row for Recipe & Ingredients
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier
                .fillMaxWidth()
               // .background(color = Color(0xFF6D4C41))
        ) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                Text("Recipe", modifier = Modifier.padding(8.dp))
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                Text("Ingredients", modifier = Modifier.padding(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Content based on selected tab
        if (selectedTab == 0) {
            RecipeView(category = category)
        } else {
            IngredientView(category = category)
        }
    }
}

@Composable
fun RecipeView(category: Meals) {

    Text(
        text = "STEPS TO BE FOLLOWED:- ",
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = category.strInstructions,
        textAlign = TextAlign.Justify,
        fontSize = 16.sp,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun IngredientView(category: Meals) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val ingredients = listOf(
            category.strIngredient1,
            category.strIngredient2,
            category.strIngredient3,
            category.strIngredient4,
            category.strIngredient5,
            category.strIngredient6,
            category.strIngredient7,
            category.strIngredient8
        ).filter { it.isNotBlank() }

        Text(
            text = "INGREDIENTS REQUIRED:- ",
            fontWeight = FontWeight.SemiBold
            )
        Spacer(modifier = Modifier.height(16.dp))

        ingredients.forEachIndexed { index, item ->
            Text(
                text = "${index + 1}. $item",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
