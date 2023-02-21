package com.example.Admin.AdminHome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.Data.PriceModel
import com.example.rig.databinding.FragmentAdminHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminHomeFragment : Fragment() {

//    companion object {
//        fun newInstance() = AdminHomeFragment()
//    }
    lateinit var binding: FragmentAdminHomeBinding
//    private val progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentAdminHomeBinding.inflate(inflater, container, false)
        SaveDataBtn()
        return binding.root
    }

    private fun SaveDataBtn(){
        binding.saveBtn.setOnClickListener {
            val metal =  binding.metalPrice.text.toString()
            val glass = binding.glassPrice.text.toString()
            val paper = binding.paperPrice.text.toString()
            val organic = binding.organicPrice.text.toString()
            val oil = binding.oilPrice.text.toString()
//            progressBar!!.visibility = View.VISIBLE
            saveFireStore(metal,glass,paper,organic,oil)

        }

    }
    fun saveFireStore(metal:String, glass:String, paper:String,organic:String,oil:String) {
val model  = PriceModel(metal,glass,paper,organic,oil)
        val db = FirebaseFirestore.getInstance()


        db.collection("Admin/")
            .document()
            .set(model)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "record added successfully ", Toast.LENGTH_SHORT ).show()
            }
            .addOnFailureListener{
                it.message?.let { it1 -> Log.d("error", it1) }
                Toast.makeText(requireContext(), "record Failed to add ", Toast.LENGTH_SHORT ).show()

            }

    }
}

