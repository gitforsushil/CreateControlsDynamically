package com.example.createcontrolsdynamically.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment And This TEXT comes from the ViewModel class."
    }
    val text: LiveData<String> = _text
}