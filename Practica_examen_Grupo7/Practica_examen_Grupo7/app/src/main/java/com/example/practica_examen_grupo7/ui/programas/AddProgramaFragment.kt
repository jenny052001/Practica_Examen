package com.example.practica_examen_grupo7.ui.programas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practica_examen_grupo7.R
import com.example.practica_examen_grupo7.databinding.FragmentAddProgramaBinding
import com.example.practica_examen_grupo7.model.Programa
import com.example.practica_examen_grupo7.viewmodel.ProgramaViewModel


class AddProgramaFragment : Fragment() {

    private var _binding: FragmentAddProgramaBinding? = null
    private val binding get() = _binding!!
    private lateinit var programaViewModel: ProgramaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProgramaBinding.inflate(inflater, container, false)

        programaViewModel =
            ViewModelProvider(this)[ProgramaViewModel::class.java]
        _binding = FragmentAddProgramaBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener {

            addPrograma()
        }

        return binding.root
    }


    private fun addPrograma() {
        val nombreprograma = binding.etNombrePrograma.text.toString()
        val fecha_lanzamiento = binding.etLanzamiento.text.toString().toInt()
        val visualizaciones = binding.etVisualizaciones.text.toString().toDouble()
        val actores = binding.etActores.text.toString()

        if (nombreprograma.isNotEmpty()) {
            val programa = Programa("","",0,0 ,"")
            programaViewModel.savePrograma(programa)
            Toast.makeText(requireContext(),getString(R.string.msg_programa_added), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addProgramaFragment_to_nav_home) // esta funcion nos envia a la pantalla principal


        } else { // mensaje de error
            Toast.makeText(requireContext(),getString(R.string.msg_data), Toast.LENGTH_SHORT).show()
        }
    }
}