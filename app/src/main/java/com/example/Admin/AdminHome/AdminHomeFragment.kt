package com.example.Admin.AdminHome

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rig.Model.PriceModel
import com.example.rig.Model.PricntageModel
import com.example.rig.Model.WeightModel
import com.example.rig.databinding.FragmentAdminHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlin.math.abs

class AdminHomeFragment : Fragment() {

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var binding: FragmentAdminHomeBinding
    lateinit var userData: PriceModel
    lateinit var plast: String

    //    private val progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminHomeBinding.inflate(inflater, container, false)

        SaveDataBtn()
        getpercentage()
        getWeight()

        if (savedInstanceState != null) {
            plast = savedInstanceState.getString("testt").toString()
//            binding.plasticPrice.setText(String.)
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        plast = binding.plasticPrice.text.toString()
        outState.putString("testt", plast)
    }

    private fun SaveDataBtn() {
        binding.saveBtn.setOnClickListener {
            val metal = binding.metalPrice.text.toString()
            val organic = binding.organicPrice.text.toString()
            val paper = binding.paperPrice.text.toString()
            val plastic = binding.plasticPrice.text.toString()
            val glass = binding.glassPrice.text.toString()
            val oil = binding.oilPrice.text.toString()
//            progressBar!!.visibility = View.VISIBLE
//            saveFireStore(metal,organic,paper,plastic,glass,oil)
            userData = PriceModel(
//                lost ,
                metal,
                organic,
                paper,
                plastic,
                glass,
                oil
            )

            setFireStore(userData)

        }

    }

    //    TODO: push data to fire store
    private fun setFireStore(userData: PriceModel) {
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
        oil: String
    ) {
        val model = PriceModel(metal, organic, paper, plastic, glass, oil)
        FirebaseDatabase.getInstance().getReference("PriceData")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(model)
        Toast.makeText(requireContext(), "Data Add", Toast.LENGTH_SHORT).show()

    }

    //    TODO: ProgressBar Handling
    fun updateProgressBar() {
        binding.progressBarTvMetal.text = metal
        binding.progressBarMetal.setProgress(metal.toInt(), true)

        binding.progressBarTvOrganic.text = organic
        binding.progressBarOrganic.setProgress(organic.toInt(), true)

        binding.progressBarTvPlastic.text = plastic
        binding.progressBarPlastic.setProgress(plastic.toInt(), true)

        binding.progressBarTvPaper.text = paper
        binding.progressBarPaper.setProgress(paper.toInt(), true)

        binding.progressBarTvGlass.text = glass
        binding.progressBarGlass.setProgress(glass.toInt(), true)

        binding.progressBarTvOil.text = oil
        binding.progressBarOil.setProgress(oil.toInt(), true)
    }

    var metal = ""
    var organic = ""
    var plastic = ""
    var paper = ""
    var glass = ""
    var oil = ""

//    var lost = ""

    //    TODO: get data from real time
    private fun getpercentage() {
        FirebaseDatabase.getInstance().getReference("levels")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val percentagemodel = snapshot.getValue(PricntageModel::class.java)
                    metal = percentagemodel!!.metal!!.let { abs(it) }.toString()
                    organic = percentagemodel!!.organic!!.let { abs(it) }.toString()
                    plastic = percentagemodel!!.plastic!!.let { abs(it) }.toString()
                    paper = percentagemodel!!.paper!!.let { abs(it) }.toString()
                    glass = percentagemodel!!.glass!!.let { abs(it) }.toString()
                    oil = percentagemodel!!.oil!!.let { abs(it) }.toString()
//                    lost = percentagemodel!!.lost.toString()


                    Log.d(ContentValues.TAG, "hsf" + snapshot.toString())
                    updateProgressBar()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    private fun getWeight() {
        FirebaseDatabase.getInstance().getReference("weight")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val weightmodel = snapshot.getValue(WeightModel::class.java)
                    binding.metalWait.text = weightmodel!!.metal!!.let { abs(it) }.toString()
                    binding.organicWait.text = weightmodel!!.organic!!.let { abs(it) }.toString()
                    binding.paperWait.text = weightmodel!!.paper!!.let { abs(it) }.toString()
                    binding.plasticWait.text = weightmodel!!.plastic!!.let { abs(it) }.toString()
                    binding.glassWait.text = weightmodel!!.glass!!.let { abs(it) }.toString()
                    binding.oilWait.text = weightmodel!!.oil!!.let { abs(it) }.toString()

                    Log.d(ContentValues.TAG, "hsf" + snapshot.toString())
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }
}

