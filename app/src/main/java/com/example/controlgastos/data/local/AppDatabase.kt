package com.example.controlgastos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Base de datos local de la aplicación.
 * Define las entidades y expone los DAO necesarios.
 */
@Database(
    entities = [ExpenseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}