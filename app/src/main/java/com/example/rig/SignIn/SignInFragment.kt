package com.example.rig.SignIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.rig.R
import com.example.rig.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

//    companion object {
//        fun newInstance() = SignInFragment()
//    }

    lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    val loginviewmodel by lazy { SignInViewModel() }
    var pressed = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        tvLogIn()
        btnRegister()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun tvLogIn() {
        binding.loginTvRegister.setOnClickListener {

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_signInFragment_to_signUpFragment)

        }
    }

    private fun btnRegister() {
        binding.loginBtnButton.setOnClickListener {
//            binding.apply {
//                val email = loginTvEmail.text.toString()
//                val passsword = loginTvPassword.text.toString()
//                loginviewmodel.logInFirebase(email, passsword)
//                getToken()
//            }
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_signInFragment_to_userMainFragment)
        }
    }
}