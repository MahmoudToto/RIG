package com.example.User.UserSetting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rig.R
import com.example.rig.databinding.FragmentUserSettingBinding
import com.google.firebase.auth.FirebaseAuth

class UserSettingFragment : Fragment() {


    lateinit var binding: FragmentUserSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserSettingBinding.inflate(inflater, container, false)

        aboutUs()
        help()
        logOut()

        return binding.root
    }

    fun saftey(){

    }
    fun aboutUs(){
        binding.cardViewAbout.setOnClickListener {
            Toast.makeText(requireContext(),"Soon by RIG Team",Toast.LENGTH_SHORT).show()
        }
    }
    fun help(){
        binding.cardViewHelp.setOnClickListener {
            Toast.makeText(requireContext(),"Soon",Toast.LENGTH_SHORT).show()
        }
    }
    fun logOut() {
        var logout = FirebaseAuth.getInstance()
        binding.cardViewLogOut.setOnClickListener {
            logout.signOut()
            findNavController().navigate(R.id.action_userSettingFragment_to_signInFragment)
        }
    }

}