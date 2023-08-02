package com.example.ejercicio2m6.Model

import androidx.lifecycle.LiveData
import com.example.ejercicio2m6.Model.Modelo.Producto
import com.example.ejercicio2m6.Model.Modelo.ProductosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductosRepository(private val productosDao: ProductosDao) {

    val listAllProducto: LiveData<List<Producto>> = productosDao.getAllProductos()

    suspend fun insertProducto(producto: Producto){
        productosDao.insertProducto(producto)
    }

    suspend fun updateProducto(producto: Producto){
        productosDao.updateProducto(producto)
    }

    suspend fun deleteProducto(producto: Producto){
        productosDao.deleteProducto(producto)
    }

    suspend fun deleteAllProductos(){
        withContext(Dispatchers.IO) {
            productosDao.deleteAll()
        }}


}