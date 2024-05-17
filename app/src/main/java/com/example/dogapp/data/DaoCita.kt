package com.example.dogapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dogapp.model.Cita

@Dao
interface DaoCita {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarCita(cita: Cita)

    @Query("SELECT * FROM Cita WHERE id = :id")
    fun getCitaById(id: Int): LiveData<Cita>
    @Update
    suspend fun editarCita(cita: Cita)
}