package com.example.User.UserHome

import android.content.ContentValues
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


             getDataPrice()
//             getpercentage()
             getWeight()

        return binding.root
    }


    //  TODO: get data frome fire store
    private fun getDataPrice() {
        Log.d("GGG","GDgdgds")
        FirebaseFirestore.getInstance().collection("PriceData")
            .document("6muGLS45UiN00wu6tJQT4VIUVKk1").addSnapshotListener { value, error ->
                binding.metalPrice.text = value!!.getString("metal")
                binding.organicPrice.text = value!!.getString("organic")
                binding.paperPrice.text = value!!.getString("paper")
                binding.plasticPrice.text = value!!.getString("plastic")
                binding.glassPrice.text = value!!.getString("glass")
                binding.oilPrice.text = value!!.getString("oil")
//                lost =value!!.getString("lost").toString()
//                binding.lostPrice.text = value!!.getString("lost")
            }
    }





    //TODO: get weight data
    private fun getWeight() {
        FirebaseDatabase.getInstance().getReference("weight")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val weightmodel = snapshot.getValue(WeightModel::class.java)
                    binding.metalWait.text = weightmodel!!.metal!!.let { abs(it) }.toString()
                    binding.plasticWait.text = weightmodel!!.plastic!!.let { abs(it) }.toString()
                    binding.organicWait.text = weightmodel!!.organic!!.let { abs(it) }.toString()
                    binding.paperWait.text = weightmodel!!.paper!!.let { abs(it) }.toString()
                    binding.glassWait.text = weightmodel!!.glass!!.let { abs(it) }.toString()
                    binding.oilWait.text = weightmodel!!.oil!!.let { abs(it) }.toString()

                    Log.d(ContentValues.TAG, "hsf" + snapshot.toString())
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

}