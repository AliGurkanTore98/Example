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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivityViewModel : ViewModel(){

    private var job : Job? = null
    val todoList = MutableLiveData<ArrayList<Todo>>()
    private val apiClient = ApiClient.getClient().create(TodoAPI::class.java)


    fun downloadData(){

        job = viewModelScope.launch(Dispatchers.IO){
            val response = apiClient.getTodos()

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let {
                        todoList.value = it
                    }
                }
            }
        }
    }
}