package com.example.controlgastos.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlgastos.data.local.ExpenseEntity
import com.example.controlgastos.data.repository.ExpenseRepository
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla de registro de gastos.
 * Gestiona el estado del formulario y guarda nuevos gastos en la base de datos.
 */
class AddExpenseViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    fun saveExpense(
        title: String,
        amountText: String,
        category: String,
        description: String,
        onSuccess: () -> Unit
    ) {
        val amount = amountText.toDoubleOrNull()

        if (title.isBlank() || amount == null || amount <= 0.0 || category.isBlank()) {
            return
        }

        viewModelScope.launch {
            val expense = ExpenseEntity(
                title = title.trim(),
                amount = amount,
                category = category.trim(),
                description = description.trim()
            )

            repository.insertExpense(expense)
            onSuccess()
        }
    }
}