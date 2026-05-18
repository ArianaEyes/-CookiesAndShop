package com.ariana.cookiesandshop.models

import java.math.BigDecimal

data class Postres(
    val id_postre: Int,
    val nom_postre: String,
    val id_tipo: Int,
    val receta: String,
    val precio: Double,
    val disponible: Int,
    val stock: Int,
    val imagen: String,
    val descripcion: String
)
