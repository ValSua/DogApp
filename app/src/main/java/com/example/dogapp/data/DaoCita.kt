package com.example.dogapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.dogapp.model.Cita

@Dao
interface DaoCita {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarCita(cita: Cita)
}