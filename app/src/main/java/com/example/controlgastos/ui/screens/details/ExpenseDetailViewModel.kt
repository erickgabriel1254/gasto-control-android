package com.example.controlgastos.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlgastos.data.local.ExpenseEntity
import com.example.controlgastos.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla de detalle.
 * Obtiene un gasto específico y permite actualizarlo o eliminarlo.
 */
class ExpenseDetailViewModel(
    private val repository: ExpenseRepository,
    expenseId: Int
) : ViewModel() {

    val expense: StateFlow<ExpenseEntity?> =
        repository.getExpenseById(expenseId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )

    fun updateExpense(
        currentExpense: ExpenseEntity,
        title: String,
        amountText: String,
        category: String,
        description: String,
        onUpdated: () -> Unit
    ) {
        val amount = amountText.toDoubleOrNull()

        if (title.isBlank() || amount == null || amount <= 0.0 || category.isBlank()) {
            return
        }

        viewModelScope.launch {
            val updatedExpense = currentExpense.copy(
                title = title.trim(),
                amount = amount,
                category = category.trim(),
                description = description.trim()
            )

            repository.updateExpense(updatedExpense)
            onUpdated()
        }
    }

    fun deleteExpense(
        expense: ExpenseEntity,
        onDeleted: () -> Unit
    ) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
            onDeleted()
        }
    }
}