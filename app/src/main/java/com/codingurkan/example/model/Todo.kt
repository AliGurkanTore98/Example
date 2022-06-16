package com.codingurkan.example.model

import java.io.Serializable

data class Todo(
    val completed: Boolean? = null,
    val id: Int? = null,
    val title: String? = null,
    val userId: Int? = null
): Serializable
