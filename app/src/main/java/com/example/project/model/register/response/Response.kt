package com.example.project.model.register.response

data class Response(
    val created_at: String,
    val id: Int,
    val name: String,
    val password: String,
    val phone: String,
    val updated_at: String
)