package com.example.ejercicio2m6.View

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ejercicio2m6.Model.Modelo.Producto
import com.example.ejercicio2m6.R
import com.example.ejercicio2m6.databinding.ProductosListaBinding


class Produtosdapter() : RecyclerView.Adapter<Produtosdapter.ViewHolder>() {

    private var listProductos = mutableListOf<Producto>()

    fun update(list: List<Producto>) {
        listProductos.clear()
        listProductos.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Produtosdapter.ViewHolder {
        return ViewHolder(ProductosListaBinding.inflate(LayoutInflater.from(parent.context)))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listProductos[position]
          holder.bind(item)
    }

    override fun getItemCount(): Int = listProductos.size

    inner class ViewHolder(private val binding: ProductosListaBinding ) : RecyclerView.ViewHolder(binding.root) {

        fun bind (producto: Producto){
            binding.nombreRV.text = binding.root.context.getString(R.string.NombreRV, producto.nombre)
            binding.precioRV.text = binding.root.context.getString(R.string.PrecioRV, producto.precio)
            binding.cantidadRV.text = binding.root.context.getString(R.string.CantidadRV, producto.cantidad)

        }
    }

}