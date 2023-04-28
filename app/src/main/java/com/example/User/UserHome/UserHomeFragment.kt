package com.example.User.UserHome

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RestrictTo.Scope
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rig.Model.PriceModel
import com.example.rig.Model.WeightModel
import com.example.rig.databinding.FragmentUserHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs


class UserHomeFragment : Fragment() {

    lateinit var binding: FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUserHomeBinding.inflate(inflater, container, false)

        binding = FragmentUserHomeBinding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch (){
            launch { getDataPrice() }
            launch { getpercentage()}
            launch { getWeight() }
        }
        return binding.root
    }


    //  TODO: get data frome fire store
    suspend private fun getDataPrice() {
        Log.d("GGG","GDgdgds")
        FirebaseFirestore.getInstance().collection("PriceData")
            .document("GyLIOUSo8OeQR9g5m48rWfLPcyE3").addSnapshotListener { value, error ->
//                lost =value!!.getString("lost").toString()
//                binding.lostPrice.text = value!!.getString("lost")
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
//        binding.progressBarTvLost.text = lost
//        binding.progressBarLost.setProgress(lost.toInt(), true)

        binding.progressBarTvPlastic.text = plastic
        binding.progressBarPlastic.setProgress(plastic.toInt(), true)

        binding.progressBarTvGlass.text = glass
        binding.progressBarGlass.setProgress(glass.toInt(), true)

        binding.progressBarTvMetal.text = metal
        binding.progressBarMetal.setProgress(metal.toInt(), true)

        binding.progressBarTvPaper.text = paper
        binding.progressBarPaper.setProgress(paper.toInt(), true)

        binding.progressBarTvOrganic.text = organic
        binding.progressBarOrganic.setProgress(organic.toInt(), true)

        binding.progressBarTvOil.text = oil
        binding.progressBarOil.setProgress(oil.toInt(), true)
    }

    var lost = ""
    var plastic = ""
    var glass = ""
    var metal = ""
    var paper = ""
    var organic = ""
    var oil = ""

//     TODO: get data from real time
    suspend private fun getpercentage() {
        Log.d("GGG","fsafsa")
        FirebaseDatabase.getInstance().getReference("PriceData")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val percentagemodel = snapshot.child("GyLIOUSo8OeQR9g5m48rWfLPcyE3")
                        .getValue(PriceModel::class.java)
                    lost = percentagemodel!!.lost.toString()
                    plastic = percentagemodel!!.plastic.toString()
                    glass = percentagemodel!!.glass.toString()
                    metal = percentagemodel!!.metal.toString()
                    paper = percentagemodel!!.paper.toString()
                    organic = percentagemodel!!.organic.toString()
                    oil = percentagemodel!!.oil.toString()
                    updateProgressBar()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    //TODO: get weight data
   suspend private fun getWeight() {
        FirebaseDatabase.getInstance().getReference("weight")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val weightmodel = snapshot.getValue(WeightModel::class.java)
                    Log.d("GGG","FSAfsa")
                    binding.plasticWait.text = weightmodel!!.weight1!!.let { abs(it) }.toString()
                    binding.glassWait.text = weightmodel!!.weight2!!.let { abs(it) }.toString()
                    binding.metalWait.text = weightmodel!!.weight3!!.let { abs(it) }.toString()
                    binding.paperWait.text = weightmodel!!.weight4!!.let { abs(it) }.toString()
                    binding.organicWait.text = weightmodel!!.weight5!!.let { abs(it) }.toString()
                    binding.oilWait.text = weightmodel!!.weight6!!.let { abs(it) }.toString()

                    Log.d(TAG, "hsf" + snapshot.toString())
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

}