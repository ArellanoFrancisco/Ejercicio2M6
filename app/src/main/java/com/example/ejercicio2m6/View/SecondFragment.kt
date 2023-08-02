package com.example.ejercicio2m6.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio2m6.Model.Modelo.Producto
import com.example.ejercicio2m6.R
import com.example.ejercicio2m6.ViewModel.ProductosViewModel
import com.example.ejercicio2m6.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    val viewModel: ProductosViewModel by viewModels()
    private var selectedCantidad = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = binding.spinner
        val valortotal = binding.TotalProducto
        val numbers = Array(101) { i -> i }

        val Adapter = ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_dropdown_item, numbers)
        spinner.adapter = Adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCantidad = numbers[position]
                val precioText = binding.precioProducto.text.toString()
                val precio = if (precioText.isNotEmpty()) precioText.toInt() else 0
                var total =  selectedCantidad * precio
                valortotal.text = binding.root.context.getString(R.string.total, total)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), R.string.toast, Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonAcept.setOnClickListener {
            val nombre = binding.nombreProducto.text.toString()
            val precioText = binding.TotalProducto.text.toString()
            val precio = if (precioText.isNotEmpty()) precioText.toInt() else 0

            if (nombre.isEmpty() || selectedCantidad == 0 || precio == 0) {
                Toast.makeText(requireContext(), "Debe agregar todos los Datos", Toast.LENGTH_SHORT).show()
            }else {
                val newProducto = Producto(nombre = nombre, cantidad = selectedCantidad, precio = precio)
                viewModel.insertProductos(newProducto)
                Toast.makeText(requireContext(), "Producto agregado", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}