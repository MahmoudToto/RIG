package com.example.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.User.UserHome.UserHomeFragment
import com.example.rig.R
import com.example.User.UserSetting.UserSettingFragment
import com.example.User.Userprofile.UserprofileFragment
import com.example.rig.databinding.FragmentUserMainBinding

class MainUserFragment : Fragment() {
    lateinit var binding:FragmentUserMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserMainBinding.inflate(inflater, container, false)
        fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutUser, UserHomeFragment())?.commit()

        bottomNavigation()
        return binding.root
    }
    fun bottomNavigation() {
        binding.bottomNavigationUser.setOnItemSelectedListener {
            lateinit var fragment: Fragment

            when (it.itemId) {
                R.id.home -> fragment = UserHomeFragment()
                R.id.setting -> fragment = UserSettingFragment()
                R.id.profile -> fragment = UserprofileFragment()
            }
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutUser, fragment)?.commit()
            return@setOnItemSelectedListener true
        }
    }

}