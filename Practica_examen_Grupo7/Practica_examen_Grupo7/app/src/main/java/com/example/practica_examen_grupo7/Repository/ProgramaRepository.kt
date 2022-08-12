package com.example.practica_examen_grupo7.Repository

import androidx.lifecycle.MutableLiveData
import com.example.practica_examen_grupo7.data.ProgramaDao
import com.example.practica_examen_grupo7.model.Programa


class ProgramaRepository(private val  programaDao: ProgramaDao) {

    fun savePrograma(programa: Programa) {
        programaDao.savePrograma(programa)
    }

    fun deletePrograma(programa: Programa) {
        programaDao.deletePrograma(programa)
    }

    val getAllData : MutableLiveData<List<Programa>> = programaDao.getAllData()
}