package com.codingurkan.example.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingurkan.example.model.Todo
import com.codingurkan.example.service.ApiClient
import com.codingurkan.example.service.TodoAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel : ViewModel() {

    private var job : Job? = null
    private val apiClient = ApiClient.getClient().create(TodoAPI::class.java)
    val currentData = MutableLiveData<Todo>()

    fun downloadDetails(){
        job = viewModelScope.launch(Dispatchers.IO){
            val response = apiClient.getDetails()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let {
                        currentData.value = it
                    }
                }
            }
        }
    }
}