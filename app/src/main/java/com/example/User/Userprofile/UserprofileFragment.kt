package com.example.User.Userprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rig.R
import com.example.rig.databinding.FragmentUserProfileBinding

class UserprofileFragment : Fragment() {
private lateinit var binding: FragmentUserProfileBinding
    companion object {
        fun newInstance() = UserprofileFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)

        back()

        return binding.root
    }
    fun back(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.mainUserFragment)
        }
    }

}