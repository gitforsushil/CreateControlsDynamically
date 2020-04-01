package com.example.createcontrolsdynamically.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This will take you to PMobile."
    }
    val text: LiveData<String> = _text
}