package com.example.appgeniushub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appgeniushub.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Получение данных из Bundle
        val userName = arguments?.getString("userName")
        val userEmail = arguments?.getString("userEmail")
        val userPassword = arguments?.getString("userPassword")
        val userId = arguments?.getInt("userId", 0) ?: 0

        // Установка данных в макет
        binding.nname.text = userName
        binding.eemail.text = userEmail
        binding.iid.text = userId.toString()


        // Другие поля устанавливаются аналогичным образом

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}