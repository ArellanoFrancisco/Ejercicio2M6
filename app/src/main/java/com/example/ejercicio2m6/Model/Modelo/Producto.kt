package com.example.ejercicio2m6.Model.Modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TABLE_PRODUCTOS")
data class Producto (

        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @ColumnInfo(name = "Name")
        val nombre: String,
        @ColumnInfo(name = "Precio")
        val precio: Int,
        @ColumnInfo(name = "Cantidad")
        val cantidad: Int
)