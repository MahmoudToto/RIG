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
import com.example.rig.Model.User
import com.example.rig.databinding.FragmentAdminHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class AdminHomeFragment : Fragment() {


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
            val plastic = binding.plasticPrice.text.toString()
            val paper = binding.paperPrice.text.toString()
            val organic = binding.organicPrice.text.toString()
            val oil = binding.oilPrice.text.toString()
//            progressBar!!.visibility = View.VISIBLE
            saveFireStore(metal,plastic,paper,organic,oil)

        }

    }
    fun saveFireStore(metal:String, plastic:String, paper:String,organic:String,oil:String) {


        val model  = PriceModel(metal,plastic,paper,organic,oil)

        FirebaseDatabase.getInstance().getReference("PriceData")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(model)



    }
}

