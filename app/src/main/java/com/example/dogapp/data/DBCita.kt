package com.example.dogapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogapp.model.Cita
import com.example.dogapp.utils.Constantes.NAME_DB

@Database(entities = [Cita::class], version = 1)
abstract class DBCita : RoomDatabase() {
    abstract fun citasDao(): DaoCita

    companion object{
        fun getDatabase(context: Context): DBCita{
            return Room.databaseBuilder(
                context.applicationContext,
                DBCita::class.java,
                NAME_DB
            ).build()
        }
    }
}