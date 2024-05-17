package com.example.dogapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Cita(
    val nombreMascota: String,
    val raza: String,
    val nombrePropietario: String,
    val tele: String,
    val sintoma: String,
    var img: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
): Serializable
