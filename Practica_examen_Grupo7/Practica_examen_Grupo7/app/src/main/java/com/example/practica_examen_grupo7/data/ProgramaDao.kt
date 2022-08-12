package com.example.practica_examen_grupo7.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.practica_examen_grupo7.model.Programa
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase


class ProgramaDao {
    private val coleccion1 = "programasApp"
    private val usuario= Firebase.auth.currentUser?.email.toString()
    private val coleccion2 = "misProgamas"
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData() : MutableLiveData<List<Programa>> {
        val listaPrograma = MutableLiveData<List<Programa>>()
        firestore.collection(coleccion1).document(usuario).collection(coleccion2)
            .addSnapshotListener{ instantanea, e ->
                if (e != null) {  //Se valida si se generó algún error en la captura de los documentos
                    return@addSnapshotListener
                }
                if (instantanea != null) {  //Si hay información recuperada...
                    //Recorro la instantanea (documentos) para crear la lista de lugares
                    val lista = ArrayList<Programa>()
                    instantanea.documents.forEach {
                        val programa = it.toObject(Programa::class.java)
                        if (programa!=null) { lista.add(programa) }
                    }
                    listaPrograma.value=lista
                }
            }
        return listaPrograma
    }

    fun savePrograma(programa: Programa) {
        val documento: DocumentReference
        if (programa.id.isEmpty()) {  //Si id no tiene valor entonces es un documento nuevo
            documento = firestore.collection(coleccion1).document(usuario).collection(coleccion2).document()
            programa.id = documento.id
        } else {  //si el id tiene valor... entonces el documento existe... y recupero la info de él
            documento = firestore.collection(coleccion1).document(usuario)
                .collection(coleccion2).document(programa.id)
        }
        documento.set(programa)
            .addOnSuccessListener { Log.d("savePrograma","Se creó o modificó un lugar") }
            .addOnCanceledListener { Log.e("savePrograma","NO se creó o modificó un lugar") }
    }

    fun deletePrograma(programa: Programa) {
        if (programa.id.isNotEmpty()) {  //Si el id tiene valor... entonces podemos eliminar el programa... porque existe...
            firestore.collection(coleccion1).document(usuario)
                .collection(coleccion2).document(programa.id).delete()
                .addOnSuccessListener { Log.d("deletePrograma","Se elimintó un programa") }
                .addOnCanceledListener { Log.e("deletePrograma","NO se eliminó programa") }
        }
    }
}