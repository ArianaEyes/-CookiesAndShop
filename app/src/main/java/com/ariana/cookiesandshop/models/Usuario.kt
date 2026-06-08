package com.ariana.cookiesandshop.models

data class Usuario(
    val id_usuario: Int,
    val nombre: String,
    val email: String,
    val password: String,
    val telefono: String,
    val id_rol:Int
)
data class  LoginRequest(
    val email: String,
    val password: String
)
