package com.example.appgeniushub.ui.support
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SupportViewModel: ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        _text.value = "This is a support fragment"
    }
}