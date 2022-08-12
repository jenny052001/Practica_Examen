package com.example.practica_examen_grupo7.ui.programas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica_examen_grupo7.R
import com.example.practica_examen_grupo7.adapter.ProgramaAdapter
import com.example.practica_examen_grupo7.databinding.FragmentAddProgramaBinding
import com.example.practica_examen_grupo7.databinding.FragmentHomeBinding
import com.example.practica_examen_grupo7.model.Programa
import com.example.practica_examen_grupo7.viewmodel.ProgramaViewModel


class ProgramasFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var programaViewModel: ProgramaViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        programaViewModel =
            ViewModelProvider(this).get(ProgramaViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.addPrograma.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_addProgramaFragment)
        }

        //Activar el reciclador...
        val ProgramaAdapter=ProgramaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = ProgramaAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        programaViewModel=ViewModelProvider(this)[ProgramaViewModel::class.java]

        programaViewModel.getAllData.observe(viewLifecycleOwner) {programas ->

            ProgramaAdapter.setData(programas)
        }



        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}