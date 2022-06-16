package com.codingurkan.example.service

import com.codingurkan.example.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoAPI {

    @GET("/todos")
    suspend fun getTodos() : Response<ArrayList<Todo>>

    @GET("details")
    suspend fun getDetails() : Response<Todo>
}