package com.example.practica_examen_grupo7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_examen_grupo7.databinding.ProgramaFlaBinding
import com.example.practica_examen_grupo7.model.Programa

class ProgramaAdapter: RecyclerView.Adapter<ProgramaAdapter.ProgramaViewHolder>() {

    private var lista = emptyList<Programa>()

    class ProgramaViewHolder(private val itemBinding: ProgramaFlaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun dibuja(programa: Programa) {
            itemBinding.tvNombrePrograma.text = programa.nombrePrograma
            itemBinding.tvFecha.inputType = programa.fecha_lanzamiento
            itemBinding.tvActores.text = programa.Actores

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgramaViewHolder { // cuando se crea una cajita en memoria
        val itemBinding = ProgramaFlaBinding.inflate(
            LayoutInflater.from(parent.context), // poder cosntruir la cajita
            parent, false
        )
        return ProgramaViewHolder(itemBinding) // devuelve la cajita con la información
    }

    // Acá se va a solicitar "dibujar" una cajita, según el elemento de lista.
    override fun onBindViewHolder(holder: ProgramaViewHolder, position: Int) {

        val programa = lista[position]
        holder.dibuja(programa)

    }

    override fun getItemCount(): Int {

        return lista.size
    }

    fun setData(programas: List<Programa>) {
        lista = programas
        notifyDataSetChanged()
    }
}

