package com.example.createcontrolsdynamically.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SlideshowViewModel : ViewModel() {
    private var rndmNumber: Int = 0
    private val _text = MutableLiveData<String>().apply {
        value = "This is Pickup screen. And this control is created dynamically. "
    }
    val text: LiveData<String> = _text

    fun updateText() {
        rndmNumber = Random.nextInt(0,1000)
        _text.postValue("This is the new text with a Randome number $rndmNumber.")
    }
}