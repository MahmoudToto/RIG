package com.example.Admin.AdminHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rig.Model.PriceModel
import com.example.rig.databinding.FragmentAdminHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class AdminHomeFragment : Fragment() {

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var binding: FragmentAdminHomeBinding
    lateinit var userData: PriceModel

    //    private val progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        SaveDataBtn()
        return binding.root
    }

    private fun SaveDataBtn() {
        binding.saveBtn.setOnClickListener {
            val lost = binding.lostPrice.text.toString()
            val plastic = binding.plasticPrice.text.toString()
            val glass = binding.glassPrice.text.toString()
            val metal = binding.metalPrice.text.toString()
            val paper = binding.paperPrice.text.toString()
            val organic = binding.organicPrice.text.toString()
            val oil = binding.oilPrice.text.toString()
//            progressBar!!.visibility = View.VISIBLE
//            saveFireStore(metal,organic,paper,plastic,glass,oil)
            userData = PriceModel(
                lost ,
                plastic ,
                glass ,
                metal,
                paper,
                organic,
                oil
            )

            setFireStore(userData)

        }

    }

    //    TODO: push data to fire store
    private fun setFireStore(userData:PriceModel) {
        db.collection("PriceData")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .set(userData)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Data added ", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), " Data not added ", Toast.LENGTH_LONG).show()
            }
    }

    //    TODO: push data to real time
    fun setRealTime(
        metal: String,
        organic: String,
        paper: String,
        plastic: String,
        glass: String,
        oil: String) {
        val model = PriceModel(metal, organic, paper, plastic, glass, oil)
        FirebaseDatabase.getInstance().getReference("PriceData")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(model)
        Toast.makeText(requireContext(), "Data Add", Toast.LENGTH_SHORT).show()

    }
}

