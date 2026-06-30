package com.ariana.cookiesandshop.models

data class LugarDetalle(
    val id_lugar: Int,
    val titulo: String,
    val subtitulo: String,
    val latitud: Double,
    val longitud: Double,
    val radio: Int,
    val imagen: String,
    val id_postre: Int,
    // datos del JOIN con postres
    val nom_postre: String,
    val receta: String,
    val precio: Double,
    val calorias: Int,
    val stock: Int,
    val disponible: Int,
    val imagen_postre: String
)
