package com.example.mockpropertymanagmentapp.data.listeners

import androidx.lifecycle.LiveData
import com.example.mockpropertymanagmentapp.data.models.Property

interface PropertyListListener {
    fun onStarted()
    fun onSuccessful(response: LiveData<ArrayList<Property>>)
    fun onFailed(message: String)
}