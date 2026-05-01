package com.example.controlgastos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controlgastos.data.local.ExpenseEntity
import com.example.controlgastos.ui.utils.formatCurrency

/**
 * Tarjeta reutilizable para representar un gasto dentro de una lista.
 * Permite navegar al detalle del gasto cuando el usuario la selecciona.
 */
@Composable
fun ExpenseCard(
    expense: ExpenseEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = expense.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = expense.category,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = formatCurrency(expense.amount),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}