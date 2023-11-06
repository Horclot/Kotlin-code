package com.example.appgeniushub.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel: ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        _text.value = "This is a product fragment"
    }
}