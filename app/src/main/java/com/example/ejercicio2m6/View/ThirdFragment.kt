package com.example.ejercicio2m6.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio2m6.Model.Modelo.Producto
import com.example.ejercicio2m6.R
import com.example.ejercicio2m6.ViewModel.ProductosViewModel
import com.example.ejercicio2m6.databinding.FragmentThirdBinding



class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    val viewModel: ProductosViewModel by viewModels()
    private lateinit var adapter: Produtosdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = Produtosdapter() // Inicializar el adaptador aquí
        binding.RVProductos.adapter = adapter
        binding.RVProductos.layoutManager = LinearLayoutManager(context)

        viewModel.allProducto.observe(viewLifecycleOwner) { productosList ->
            adapter.update(productosList)
            val totalPrecio = productosList.sumOf { it.precio }
            val precioTotalString = resources.getString(R.string.total, totalPrecio)
            binding.totalventa.text = precioTotalString
        }

        binding.buttonfinish.setOnClickListener {
            if (viewModel.allProducto.value!!.isEmpty() == true) {
                Toast.makeText(requireContext(), "no hay productos ingresados", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.deleteAllProductos()
                findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
                Toast.makeText(requireContext(), "Venta Realizada", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
      // _binding = null
    }
}