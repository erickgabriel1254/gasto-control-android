package com.example.controlgastos.data.repository

import com.example.controlgastos.data.local.ExpenseDao
import com.example.controlgastos.data.local.ExpenseEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de aislar la capa de datos del resto de la aplicación.
 * Expone las operaciones necesarias para consultar, insertar, actualizar y eliminar gastos.
 */
class ExpenseRepository(
    private val expenseDao: ExpenseDao
) {
    fun getAllExpenses(): Flow<List<ExpenseEntity>> {
        return expenseDao.getAllExpenses()
    }

    fun getExpenseById(expenseId: Int): Flow<ExpenseEntity?> {
        return expenseDao.getExpenseById(expenseId)
    }

    suspend fun insertExpense(expense: ExpenseEntity) {
        expenseDao.insertExpense(expense)
    }

    suspend fun updateExpense(expense: ExpenseEntity) {
        expenseDao.updateExpense(expense)
    }

    suspend fun deleteExpense(expense: ExpenseEntity) {
        expenseDao.deleteExpense(expense)
    }
}