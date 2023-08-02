package com.example.ejercicio2m6.Model.Modelo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Producto::class], version = 1)
abstract class ProductosDataBase: RoomDatabase() {

    abstract fun getProductosDao(): ProductosDao

    companion object {

        @Volatile
        private var INSTANCE: ProductosDataBase? = null

        fun getDatabase(context: Context): ProductosDataBase {
            val tempInntance = INSTANCE
            // ES DISTINTO A NULL
            if (tempInntance != null) {

                return tempInntance
            }

            // LLAMA A UN METODO Y LO SINCRONIZA PARA INSTANCIAR
            synchronized(this) {
                // CLASE DE ROOM ES EL CREADOR DE LA INSTANCIA DE LA BASE DE DATOS
                val instance = Room.databaseBuilder(
                    // la bade datos sea una para toda la app
                    context.applicationContext,
                    // NOMBRE DEL ARCHIVO QUE CONTIENE LA BASE DE DATO
                    ProductosDataBase::class.java,
                    "ProductosDatabase"
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}