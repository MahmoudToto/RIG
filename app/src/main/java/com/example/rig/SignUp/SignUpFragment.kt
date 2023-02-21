package com.example.rig.SignUp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.rig.Model.User
import com.example.rig.R
import com.example.rig.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    var radioButton: RadioButton? = null

//    var radioGroup: RadioGroup? = null

    lateinit var registerViewmodel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        registerViewmodel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SignUpViewModel::class.java]
        setEvenets()
        binding.registerBtnSignup.setOnClickListener(View.OnClickListener {
            val selectedID: Int = binding.radio.checkedRadioButtonId

            Toast.makeText(requireContext(), selectedID.toString(), Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }
    private fun setEvenets(){
        binding.registerBtnSignup.setOnClickListener {
            getDataFromUser()

        }
        binding.textView.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        }
    private fun addListenerButton() {


    }

    private fun getDataFromUser() {

        binding.apply {
            val email_ = registerTvEmail.text.toString()
            val passwrod_ = registerTvPassword.text.toString()
            val name_ = registerTvName.text.toString()
            val configPassword = registerTvConfpassword.text.toString()


            if (email_.isBlank()) {
                registerTvEmail.error = "IsEmpty"
              Toast.makeText(requireContext(), "Email is Empty  ",Toast.LENGTH_SHORT).show()
            }
            if (name_.isBlank()) {
                registerTvName.error = "IsEmpty"
                Toast.makeText(requireContext(), "Name is Empty  ",Toast.LENGTH_SHORT).show()

            }
            if (passwrod_.isBlank()) {
                registerTvPassword.error = "IsEmpty"
                Toast.makeText(requireContext(), "Password  is Empty  ",Toast.LENGTH_SHORT).show()
            }
            if (configPassword.isBlank()) {
                registerTvConfpassword.error = "IsEmpty"
                Toast.makeText(requireContext(), "Password is Empty  ",Toast.LENGTH_SHORT).show()

            }
//if (binding.registerTvPassword.text.!! == binding.registerTvConfpassword.text){
//
//}
            if (email_.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(binding.registerTvEmail.text.toString())
                    .matches()
            ) {
                Toast.makeText(requireContext(), "Email is Invalid  ",Toast.LENGTH_SHORT).show()

            } else {

                val user = User(
                   "",
                    name_,
                    email_,
                    passwrod_,
                    "User"
                )
                studentRegisteration(user, email_, passwrod_)
            }
        }
    }

    private fun studentRegisteration(user: User, email: String, password: String) {
        registerViewmodel.SignUpAndUploadData(user, email, password)
            .observe(requireActivity()) {
                if (it == true) {
                    findNavController().navigate(R.id.action_signUpFragment_to_userMainFragment)
                } else {
                    Toast.makeText(requireContext(), "Faild ",Toast.LENGTH_SHORT).show()
                }
            }

    }
}