package com.example.appgeniushub.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appgeniushub.databinding.FragmentProductBinding

class CartFragment: Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var productViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root = binding.root

        // Инициализация ViewModel
        productViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        val textView = binding.textProduct
        productViewModel.text.observe(viewLifecycleOwner) { newText ->
            textView.text = newText
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
