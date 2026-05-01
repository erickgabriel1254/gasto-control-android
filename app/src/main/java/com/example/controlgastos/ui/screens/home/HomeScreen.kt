package com.example.controlgastos.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controlgastos.ui.components.ExpenseCard
import com.example.controlgastos.ui.components.SummaryCard
import androidx.compose.ui.res.stringResource
import com.example.controlgastos.R
import com.example.controlgastos.ui.utils.formatCurrency
import androidx.compose.foundation.layout.Arrangement

/**
 * Pantalla principal de la aplicación.
 * Muestra el total de gastos y la lista de registros.
 */
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAddClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {

    val expenses by viewModel.expenses.collectAsState()
    val total by viewModel.totalAmount.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text(stringResource(R.string.new_expense))
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            SummaryCard(
                title = "Total Gastado",
                value = formatCurrency(total)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)){
                items(expenses) { expense ->

                    ExpenseCard(
                        expense = expense,
                        onClick = { onItemClick(expense.id) }
                    )
                }
            }
        }
    }
}