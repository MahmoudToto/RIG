package com.example.rig.signup

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rig.Model.PriceModel
import com.example.rig.Model.User
import com.example.rig.R
import com.example.rig.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

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

        binding.registerBtnSignup.setOnClickListener(View.OnClickListener {
            //  val selectedID: Int = binding.radio.checkedRadioButtonId
            getDataFromUser(binding.root)
            //  Toast.makeText(requireContext(), selectedID.toString(), Toast.LENGTH_SHORT).show()
        })

        goToSignIn()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun goToSignIn() {

        binding.tvGoToSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

    }


    private fun getUserSTate(v: View): String {
        var id: Int = binding.radio.checkedRadioButtonId
        val radio: RadioButton = v.findViewById(id)
        return radio.text.toString()
    }

    private fun getDataFromUser(v: View) {

        binding.apply {
            val email_ = registerTvEmail.text.toString()
            val passwrod_ = registerTvPassword.text.toString()
            val name_ = registerTvName.text.toString()
            val configPassword = registerTvConfpassword.text.toString()

            if (email_.isBlank()) {
                registerTvEmail.error = "IsEmpty"
                Toast.makeText(requireContext(), "Email is Empty  ", Toast.LENGTH_SHORT).show()
            }
            if (name_.isBlank()) {
                registerTvName.error = "IsEmpty"
                Toast.makeText(requireContext(), "Name is Empty  ", Toast.LENGTH_SHORT).show()

            }
            if (passwrod_.isBlank()) {
                registerTvPassword.error = "IsEmpty"
                Toast.makeText(requireContext(), "Password  is Empty  ", Toast.LENGTH_SHORT).show()
            }
            if (configPassword.isBlank()) {
                registerTvConfpassword.error = "IsEmpty"
                Toast.makeText(requireContext(), "Password is Empty  ", Toast.LENGTH_SHORT).show()

            }
            if (email_.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(binding.registerTvEmail.text.toString())
                    .matches()
            ) {
                Toast.makeText(requireContext(), "Email is Invalid  ", Toast.LENGTH_SHORT).show()

            } else {

                val user = User(
                    "",
                    name_,
                    email_,
                    passwrod_,
                    getUserSTate(v)
                )
//                setData(user)
                studentRegisteration(user, email_, passwrod_)
            }
        }

    }
//    private fun setData(userData: User) {
//        db.collection("Users")
//            .document(FirebaseAuth.getInstance().uid.toString())
//            .set(userData)
//            .addOnSuccessListener {
//                    if (getUserSTate(binding.root) == "User") {
//                        findNavController().navigate(R.id.action_signUpFragment_to_mainUserFragment)
//                    }
//                    else if (getUserSTate(binding.root) == "Admin"){
//                        findNavController().navigate(R.id.action_signUpFragment_to_mainAdminFragment)
//                    }
//                    else if (getUserSTate(binding.root) == "Seller"){
//                        findNavController().navigate(R.id.action_signInFragment_to_mainSellerFragment)
//                    }
//                 else {
//                    Toast.makeText(requireContext(), "Faild ", Toast.LENGTH_SHORT).show()
//                }
//                Toast.makeText(requireContext(), "Data added ", Toast.LENGTH_LONG).show()
//            }
//            .addOnFailureListener {
//                Toast.makeText(requireContext(), " Data not added ", Toast.LENGTH_LONG).show()
//            }
//    }
        //  TODO: Creat Account
    private fun studentRegisteration(user: User, email: String, password: String) {
        registerViewmodel.SignUpAndUploadData(user, email, password)
            .observe(requireActivity()) {
                if (it == true) {
                    if (getUserSTate(binding.root)== "User") {
                        findNavController().navigate(R.id.action_signUpFragment_to_mainUserFragment)
                    }
                    else if (getUserSTate(binding.root) == "Admin"){
                        findNavController().navigate(R.id.action_signUpFragment_to_mainAdminFragment)
                    }
                    else if (getUserSTate(binding.root) == "Seller"){
                        findNavController().navigate(R.id.action_signInFragment_to_mainSellerFragment)
                    }
                } else {
                    Toast.makeText(requireContext(), "Faild ", Toast.LENGTH_SHORT).show()
                }
            }

    }
}