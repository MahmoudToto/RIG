package com.example.User.UserHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rig.Model.PriceModel
import com.example.rig.databinding.FragmentUserHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore


class UserHomeFragment : Fragment() {

    lateinit var binding: FragmentUserHomeBinding
    var firebaseDatabase: FirebaseDatabase? = null
    var firebaseAuth: FirebaseAuth? = null
    private lateinit var database: DatabaseReference
    private var ProgressBar = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUserHomeBinding.inflate(inflater, container, false)
//        getData()
        readData()
//        getsd()
        getDataFromFireStore()
        return binding.root
    }

    //  TODO: Progressbar

    //  TODO: get data frome fire store
    fun getDataFromFireStore() {
        FirebaseFirestore.getInstance().collection("PriceData")
            .document("GyLIOUSo8OeQR9g5m48rWfLPcyE3").addSnapshotListener { value, error ->
//                lost =value!!.getString("lost").toString()
                binding.lostPrice.text = value!!.getString("lost")
                binding.plasticPrice.text = value!!.getString("plastic")
                binding.glassPrice.text = value!!.getString("glass")
                binding.metalPrice.text = value!!.getString("metal")
                binding.paperPrice.text = value!!.getString("paper")
                binding.organicPrice.text = value!!.getString("organic")
                binding.oilPrice.text = value!!.getString("oil")

            }
    }
    //    TODO: ProgressBar Handling
    fun updateProgressBar() {
        binding.progressBarTvLost.text = lost
        binding.progressBarLost.setProgress(lost.toInt(),true)

        binding.progressBarTvPlastic.text = plastic
        binding.progressBarPlastic.setProgress(plastic.toInt(),true)

        binding.progressBarTvGlass.text = glass
        binding.progressBarGlass.setProgress(glass.toInt(),true)

        binding.progressBarTvMetal.text = metal
        binding.progressBarMetal.setProgress(metal.toInt(),true)

        binding.progressBarTvPaper.text = paper
        binding.progressBarPaper.setProgress(paper.toInt(),true)

        binding.progressBarTvOrganic.text = organic
        binding.progressBarOrganic.setProgress(organic.toInt(),true)

        binding.progressBarTvOil.text = oil
        binding.progressBarOil.setProgress(oil.toInt(),true)
    }
    var lost = ""
    var plastic = ""
    var glass = ""
    var metal = ""
    var paper = ""
    var organic = ""
    var oil = ""

    //    TODO: get data from real time
    private fun readData() {
        FirebaseDatabase.getInstance().getReference("PriceData").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val pricemodel = snapshot.child("GyLIOUSo8OeQR9g5m48rWfLPcyE3").getValue(PriceModel::class.java)
                lost = pricemodel!!.lost.toString()
                plastic = pricemodel!!.plastic.toString()
                glass = pricemodel!!.glass.toString()
                metal = pricemodel!!.metal.toString()
                paper = pricemodel!!.paper.toString()
                organic = pricemodel!!.organic.toString()
                oil = pricemodel!!.oil.toString()
                updateProgressBar()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


}