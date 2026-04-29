package com.example.controlgastos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa un gasto almacenado en la base de datos local.
 */
@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)