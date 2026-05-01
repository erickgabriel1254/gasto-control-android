package com.example.controlgastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.controlgastos.data.local.DatabaseProvider
import com.example.controlgastos.data.repository.ExpenseRepository
import com.example.controlgastos.ui.navigation.AppNavigation
import com.example.controlgastos.ui.theme.ControlGastosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = ExpenseRepository(database.expenseDao())

        setContent {
            ControlGastosTheme() {
                AppNavigation(repository = repository)
            }

        }
    }
}