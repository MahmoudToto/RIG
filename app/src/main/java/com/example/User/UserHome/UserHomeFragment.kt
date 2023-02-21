package com.example.User.UserHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rig.R
import com.example.rig.databinding.FragmentUserHomeBinding


class UserHomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentUserHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUserHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

}