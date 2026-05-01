package com.example.controlgastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.controlgastos.data.local.DatabaseProvider
import com.example.controlgastos.data.repository.ExpenseRepository
import com.example.controlgastos.ui.screens.home.HomeScreen
import com.example.controlgastos.ui.screens.home.HomeViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = ExpenseRepository(database.expenseDao())
        val viewModel = HomeViewModel(repository)

        setContent {
            HomeScreen(
                viewModel = viewModel,
                onAddClick = {},
                onItemClick = {}
            )
        }
    }
}