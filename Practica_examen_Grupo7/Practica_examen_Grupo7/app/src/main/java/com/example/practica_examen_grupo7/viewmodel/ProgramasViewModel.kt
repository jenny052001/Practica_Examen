package com.example.practica_examen_grupo7.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.practica_examen_grupo7.Repository.ProgramaRepository
import com.example.practica_examen_grupo7.data.ProgramaDao
import com.example.practica_examen_grupo7.model.Programa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProgramaViewModel(application: Application) : AndroidViewModel(application){   //Atributo para obtener la lista de lugares en un ArrayList especial
    val getAllData: MutableLiveData<List<Programa>>
    //Atributo para acceder al repositorio de Lugar
    private val repository: ProgramaRepository = ProgramaRepository(ProgramaDao())

    //Bloque de inicialización de los atributos
    init {  getAllData = repository.getAllData  }

    fun savePrograma(programa: Programa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePrograma(programa)
        }
    }

    fun deleteLugar(programa: Programa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePrograma(programa)
        }
    }
}