package com.example.controlgastos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.controlgastos.data.repository.ExpenseRepository
import com.example.controlgastos.ui.screens.add.AddExpenseScreen
import com.example.controlgastos.ui.screens.add.AddExpenseViewModel
import com.example.controlgastos.ui.screens.detail.ExpenseDetailScreen
import com.example.controlgastos.ui.screens.detail.ExpenseDetailViewModel
import com.example.controlgastos.ui.screens.home.HomeScreen
import com.example.controlgastos.ui.screens.home.HomeViewModel

/**
 * Define las rutas de navegación principales de la aplicación.
 */
object AppRoutes {
    const val HOME = "home"
    const val ADD_EXPENSE = "add_expense"
    const val DETAIL = "detail"
}

/**
 * Componente principal de navegación de la app.
 * Conecta las pantallas Home, AddExpense y ExpenseDetail.
 */
@Composable
fun AppNavigation(
    repository: ExpenseRepository
) {
    val navController = rememberNavController()

    val homeViewModel = HomeViewModel(repository)
    val addExpenseViewModel = AddExpenseViewModel(repository)

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                viewModel = homeViewModel,
                onAddClick = {
                    navController.navigate(AppRoutes.ADD_EXPENSE)
                },
                onItemClick = { expenseId ->
                    navController.navigate("${AppRoutes.DETAIL}/$expenseId")
                }
            )
        }

        composable(AppRoutes.ADD_EXPENSE) {
            AddExpenseScreen(
                viewModel = addExpenseViewModel,
                onExpenseSaved = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${AppRoutes.DETAIL}/{expenseId}",
            arguments = listOf(
                navArgument("expenseId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val expenseId = backStackEntry.arguments?.getInt("expenseId") ?: return@composable

            val detailViewModel = ExpenseDetailViewModel(
                repository = repository,
                expenseId = expenseId
            )

            ExpenseDetailScreen(
                viewModel = detailViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}