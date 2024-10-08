package com.example.project.model.user.accountBank

data class Response(
    val account: String,
    val balance: Int,
    val created_at: String,
    val id: Int,
    val updated_at: String,
    val user: User,
    val user_id: Int
)