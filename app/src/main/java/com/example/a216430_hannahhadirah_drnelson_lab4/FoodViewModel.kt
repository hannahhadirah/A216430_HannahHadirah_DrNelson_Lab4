package com.example.a216430_hannahhadirah_drnelson_lab4

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class FoodViewModel : ViewModel() {

    private val _foodList = mutableStateListOf<FoodItem>()
    val foodList: List<FoodItem> = _foodList

    fun addFood(food: FoodItem) {
        _foodList.add(food)
    }

    // 🔥 Calculate days left
    fun getDaysLeft(expiryDate: LocalDate): Long {
        return ChronoUnit.DAYS.between(LocalDate.now(), expiryDate)
    }

    // 🔥 Get expiring items (<= 3 days)
    fun getExpiringSoon(): List<FoodItem> {
        return _foodList.filter {
            getDaysLeft(it.expiryDate) <= 3
        }
    }
}