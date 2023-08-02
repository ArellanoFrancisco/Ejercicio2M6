package com.example.ejercicio2m6.Model.Modelo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducto(producto: Producto)

    @Update
    suspend fun updateProducto(producto: Producto)

    @Delete
    suspend fun deleteProducto(producto: Producto)

    @Query("DELETE FROM TABLE_PRODUCTOS")
    fun deleteAll()

    @Query("SELECT * FROM  TABLE_PRODUCTOS")
    fun getAllProductos(): LiveData<List<Producto>>

}