package com.example.project.model.invesmentUser

data class Investment(
    val created_at: String,
    val expected_return: Int,
    val id: Int,
    val minimum_balance: Int,
    val name: String,
    val updated_at: String
)