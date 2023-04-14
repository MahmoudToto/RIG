package com.example.Admin.AdminSetting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rig.R
import com.example.rig.databinding.FragmentAdminHomeBinding
import com.example.rig.databinding.FragmentAdminSettingBinding
import com.google.firebase.auth.FirebaseAuth


class AdminSettingFragment : Fragment() {

private lateinit var binding:FragmentAdminSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAdminSettingBinding.inflate(inflater, container, false)

        saftey()
        aboutUs()
        help()
        logOut()
        back()
        return binding.root
    }

    fun saftey(){
        binding.cardViewSaftey.setOnClickListener {
            Log.d("exc", "Phone error message : ")
            findNavController().navigate(R.id.action_adminSettingFragment_to_safetyFragment)
        }

    }
    fun aboutUs(){
        binding.cardViewAbout.setOnClickListener {
            Toast.makeText(requireContext(),"Soon by RIG Team", Toast.LENGTH_SHORT).show()
        }
    }
    fun help(){
        binding.cardViewHelp.setOnClickListener {
            Toast.makeText(requireContext(),"Soon", Toast.LENGTH_SHORT).show()
        }
    }
    fun logOut() {
        var logout = FirebaseAuth.getInstance()
        binding.cardViewLogOut.setOnClickListener {
            logout.signOut()
            findNavController().navigate(R.id.action_adminSettingFragment_to_signInFragment)
        }
    }
    fun back(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.mainUserFragment)
        }
    }
}