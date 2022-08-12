package com.example.practica_examen_grupo7.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize

data class Programa(
    var id: String,
    val nombrePrograma: String,
    val fecha_lanzamiento: Int,
    val visualizaciones: Int?,
    val Actores: String?,

    ) : Parcelable {
    constructor():
            this("","",0,0,"")
}