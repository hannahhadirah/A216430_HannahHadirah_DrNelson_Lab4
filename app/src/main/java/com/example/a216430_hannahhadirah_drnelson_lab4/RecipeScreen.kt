package com.example.a216430_hannahhadirah_drnelson_lab4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Brush
import com.example.a216430_hannahhadirah_drnelson_lab4.AppHeader

@Composable
fun RecipeScreen(navController: NavController, viewModel: FoodViewModel) {

    val expiringItems = viewModel.getExpiringSoon()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .padding(16.dp)

    ) {

//        header
        AppHeader(
            title = "Recipes",
            subtitle = "Smart meal suggestions",
            showProfile = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (expiringItems.isEmpty()) {
            Text("No expiring items. You're good!")
        } else {

            expiringItems.forEach { food ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {

                        Text(
                            "Use: ${food.name}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            "Recipe Idea: ${getRecipe(food.name)}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

fun getRecipe(food: String): String {
    return when (food.lowercase()) {
        "milk" -> "Pancakes 🥞"
        "bread" -> "Toast Sandwich 🥪"
        "banana" -> "Banana Smoothie 🍌"
        else -> "Simple Stir Fry 🍳"
    }
}