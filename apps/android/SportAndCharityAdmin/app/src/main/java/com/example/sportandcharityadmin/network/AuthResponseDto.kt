package com.example.sportandcharityadmin.network

import com.google.gson.annotations.SerializedName

data class AuthResponseDto(
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("password")
    val hashedPassword: String
)