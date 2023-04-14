package com.example.Admin.AdminProile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rig.R
import com.example.rig.databinding.FragmentAdminProfileBinding

class AdminProfileFragment : Fragment() {

private lateinit var binding: FragmentAdminProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAdminProfileBinding.inflate(inflater, container, false)
        back()
        return binding.root
    }

    fun back(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.mainAdminFragment)
        }
    }
}