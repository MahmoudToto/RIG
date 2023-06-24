package com.example.rig.signin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rig.R
import com.example.rig.databinding.FragmentSignInBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.rig.Model.User


class SignInFragment : Fragment() {


    lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    val loginviewmodel by lazy { SignInViewModel() }
    var pressed = false
    var email = ""
    var pass = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        tvLogIn()
//        btnRegister()

        binding.loginBtnButton.setOnClickListener {
            binding.apply {
                val email = loginTvEmail.text.toString()
                val pass = loginTvPassword.text.toString()

                if (email.isBlank()) {
                    loginTvEmail.error = "Email is Empty"
//                    Toast.makeText(requireContext(), "Email is Empty  ", Toast.LENGTH_SHORT).show()
                }
                if (pass.isBlank()) {
                    loginTvPassword.error = "Password is Empty"
//                    Toast.makeText(requireContext(), "Password  is Empty  ", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d("pass", email)
                    Log.d("email", pass)
                    signin(email, pass)
                }
//                email = loginTvEmail.text.toString().trim()
//                pass = loginTvPassword.text.toString().trim()

            }
        }
        getUserData()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun tvLogIn() {

        binding.loginTvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }


    }

    @SuppressLint("LongLogTag")
    private fun signin(email: String, pass: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.trim(), pass)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Successfully", Toast.LENGTH_SHORT).show()
//                    Navigation.findNavController(binding.root)
//                        .navigate(R.id.action_signInFragment_to_mainUserFragment)
//                    getUserData(email)
//                   var state  =  searchByEmail(email.trim())
//                    Log.d("stateUser_succeessfuly_signing",state)
//                    navigat(state)

                } else {
                    Toast.makeText(requireContext(), "Faild", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun getUserData() {

        var ref = FirebaseDatabase.getInstance().getReference("Users")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                Log.d("usss",user.toString())
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

    private fun navigat(userType: String) {
        Log.d("state", userType)

        if (userType == "Admin") {
            findNavController().navigate(R.id.action_signInFragment_to_mainAdminFragment)
        } else if (userType == "User") {
            findNavController().navigate(R.id.action_signInFragment_to_mainUserFragment)
        } else if (userType == "Seller") {
            findNavController().navigate(R.id.action_signInFragment_to_mainSellerFragment)
        } else {
            Toast.makeText(requireContext(), "Error out users", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchByEmail(email: String): String {
        var stateUser = ""
        val databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                Log.d("userdata",user.toString())
                if (user!!.email.equals(email)) {
                    stateUser = user.state
                    Log.d("stateUser_successfuly",stateUser)
                } else {
                    Toast.makeText(requireContext(), "User Not Here", Toast.LENGTH_SHORT).show()
                }


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.code, Toast.LENGTH_SHORT).show()
            }
        })
        Log.d("stateUser_return",stateUser)
        return stateUser
    }
}

//    private fun btnRegister() {
//
//        binding.loginBtnButton.setOnClickListener {
////            binding.apply {
////                val email = loginTvEmail.text.toString()
////                val passsword = loginTvPassword.text.toString()
////                loginviewmodel.logInFirebase(email, passsword)
////                getToken()
////            }
//            Navigation.findNavController(binding.root)
//                .navigate(R.id.action_signInFragment_to_mainUserFragment)
//}
//}
