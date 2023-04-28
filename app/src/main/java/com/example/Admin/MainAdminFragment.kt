package com.example.Admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.Admin.AdminHome.AdminHomeFragment
import com.example.Admin.AdminProile.AdminProfileFragment
import com.example.Admin.AdminSaftey.AdminSafteyFragment
import com.example.Admin.AdminSetting.AdminSettingFragment

import com.example.rig.R
import com.example.rig.databinding.FragmentAdminMainBinding

class MainAdminFragment : Fragment() {

    lateinit var binding: FragmentAdminMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminMainBinding.inflate(inflater, container, false)
        fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutAdmin, AdminHomeFragment())?.commit()
        bottomNavigation()
        return binding.root
    }


    fun bottomNavigation() {
        binding.bottomNavigationAdmin.setOnItemSelectedListener {
            lateinit var fragment: Fragment

            when (it.itemId) {
                R.id.home -> fragment = AdminHomeFragment()
                R.id.setting -> fragment = AdminSettingFragment()
                R.id.profile -> fragment = AdminProfileFragment()
                R.id.saftey -> fragment = AdminSafteyFragment()

            }
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutAdmin, fragment)?.commit()
            return@setOnItemSelectedListener true
        }
    }
}