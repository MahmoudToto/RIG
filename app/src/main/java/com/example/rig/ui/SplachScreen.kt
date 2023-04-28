package com.example.rig.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rig.Model.User
import com.example.rig.R
import com.example.rig.databinding.FragmentSplachScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SplachScreen : Fragment() {
    var mAuth: FirebaseAuth? = null

    val user = FirebaseAuth.getInstance().currentUser?.uid

    lateinit var binding: FragmentSplachScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplachScreenBinding.inflate(inflater, container, false)

        lifecycleScope.launchWhenStarted {
            delay(1000)
            Log.d("userstatenull","yes")
            if (user == null) {
                findNavController().navigate(R.id.action_splachScreen_to_signInFragment)
            } else {
                getUserData()
            }
        }

        return binding.root
    }

    private fun getUserData() {
        if (mAuth == null) mAuth = FirebaseAuth.getInstance()
        FirebaseDatabase.getInstance().getReference("Users").child(user.toString())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    navigat(user!!.state)
                    Log.d("state_fromserver", user.state)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("error", error.message)
                }
            })
    }

    private fun navigat(userType: String) {
        Log.d("state", userType)

        if (userType == "Admin") {
            findNavController().navigate(R.id.action_splachScreen_to_mainAdminFragment)
        } else if (userType == "User") {
            findNavController().navigate(R.id.action_splachScreen_to_mainUserFragment)
        }else if(userType == "Seller"){
            findNavController().navigate(R.id.action_splachScreen_to_mainSellerFragment)
        }
        else {
            Toast.makeText(requireContext(), "Error out users", Toast.LENGTH_SHORT).show()
        }
    }
}