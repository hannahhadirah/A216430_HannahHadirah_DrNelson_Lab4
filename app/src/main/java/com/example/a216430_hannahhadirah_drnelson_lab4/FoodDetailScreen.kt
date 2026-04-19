package com.example.a216430_hannahhadirah_drnelson_lab4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.a216430_hannahhadirah_drnelson_lab4.AppHeader
import com.example.a216430_hannahhadirah_drnelson_lab4.FoodViewModel

@Composable
fun FoodDetailScreen(navController: NavController, viewModel: FoodViewModel) {

    val foodList = viewModel.foodList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .padding(horizontal = 16.dp)
    ) {

        // header
        AppHeader(
            title = "Food List",
            subtitle = "Track all your stored food",
            showProfile = false
        )

        Spacer(modifier = Modifier.height(12.dp))

        // list food

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

// empty state
            if (foodList.isEmpty()) {
                item {
                    Text(
                        "No food added yet",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {

                // normal list

                items(foodList) { food ->

                    val daysLeft = viewModel.getDaysLeft(food.expiryDate)

                    val statusColor = when {
                        daysLeft <= 1 -> MaterialTheme.colorScheme.error
                        daysLeft <= 2 -> Color(0xFFFF9800)
                        else -> Color(0xFF4CAF50)
                    }

                    val statusText = when {
                        daysLeft <= 1 -> "URGENT"
                        daysLeft <= 2 -> "SOON"
                        else -> "FRESH"
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(statusColor)
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    "$daysLeft d",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(food.name, fontWeight = FontWeight.Bold)
                                Text("Expiry: ${food.expiryDate}")
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(statusColor.copy(alpha = 0.15f))
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(statusText, color = statusColor)
                            }
                        }
                    }
                }
            }
        }
    }
}