package com.example.ejercicio2m6.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicio2m6.Model.Modelo.Producto
import com.example.ejercicio2m6.Model.Modelo.ProductosDao
import com.example.ejercicio2m6.Model.Modelo.ProductosDataBase
import com.example.ejercicio2m6.Model.ProductosRepository
import kotlinx.coroutines.launch

class ProductosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository :ProductosRepository

    val allProducto: LiveData<List<Producto>>

init{
// obteniendo instancia del dao
    val productosDao = ProductosDataBase.getDatabase(application).getProductosDao()
    repository = ProductosRepository(productosDao)
    allProducto = repository.listAllProducto
}
    fun insertProductos(producto : Producto) = viewModelScope.launch {
        repository.insertProducto(producto)
    }

    fun updateProductos(producto : Producto) = viewModelScope.launch {
        repository.updateProducto(producto)
    }

    fun deleteProductos(producto : Producto) = viewModelScope.launch {
        repository.deleteProducto(producto)
    }
    fun deleteAllProductos() = viewModelScope.launch {
        repository.deleteAllProductos()
    }


}