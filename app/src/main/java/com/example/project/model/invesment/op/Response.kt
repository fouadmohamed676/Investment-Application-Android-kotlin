package com.example.project.model.invesment.op

data class Response(
    val created_at: String,
    val id: Int,
    val investment: Investment,
    val investment_balance: Int,
    val investment_id: Int,
    val updated_at: String,
    val user_id: Int
)