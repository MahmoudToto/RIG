package com.example.Admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
      /*  val navHostFragment =requireActivity().supportFragmentManager.findFragmentById(R.id.mainAdminFragment) as NavHostFragment
        val navController = navHostFragment.navController
//        val navController = findNavController(R.id.mainAdminFragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.signInFragment ||
                destination.id == R.id.signUpFragment ||
                destination.id == R.id.viewBagerFragment ||
                destination.id == R.id.splachScreen
            ) {
                binding.bottomNavigationAdmin.visibility = View.GONE
            } else {
                binding.bottomNavigationAdmin.visibility = View.VISIBLE
            }
        }*/

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
//                R.id.mapsFragment -> fragment = MapsFragment()

            }
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutAdmin, fragment)?.commit()
            return@setOnItemSelectedListener true
        }
    }
}


