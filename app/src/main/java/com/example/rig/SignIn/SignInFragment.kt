package com.example.rig.SignIn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.rig.Model.User
import com.example.rig.R
import com.example.rig.databinding.FragmentSignInBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


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
                email = loginTvEmail.text.toString()
                pass = loginTvPassword.text.toString()
                Log.d("pass",pass.toString())
                Log.d("email",email.toString())
                signin(email, pass)
            }
        }
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
    private fun signin(email: String, pass: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Successfully", Toast.LENGTH_SHORT).show()
//                    Navigation.findNavController(binding.root)
//                        .navigate(R.id.action_signInFragment_to_mainUserFragment)
                    getUserData(email)

                } else {
                    Toast.makeText(requireContext(), "Faild", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun getUserData(email: String) {

        var databaseReference = FirebaseDatabase.getInstance().reference.child("Users")
            val query:Query =databaseReference
                .orderByChild("email").equalTo(email)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    Log.d("emaailtest","Email here")
                }else {
                    Log.d("emaailtest","Email not here")

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun navigat(userType: String) {
        Log.d("state",userType)

        if (userType == "Admin") {
            findNavController().navigate(R.id.mainAdminFragment)
        } else if (userType == "User") {
            findNavController().navigate(R.id.mainUserFragment)
        } else {
            Toast.makeText(requireContext(), "Error out users",Toast.LENGTH_SHORT).show()
        }
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
