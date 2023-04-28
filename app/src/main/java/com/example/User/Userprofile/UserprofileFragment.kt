package com.example.User.Userprofile

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.rig.R
import com.example.rig.databinding.FragmentUserProfileBinding
import com.example.rig.ui.SharedPref

class UserprofileFragment : Fragment() {
private lateinit var binding: FragmentUserProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        if(SharedPref.getNameProfile()!=null){
            binding.editNameText.text=SharedPref.getNameProfile()
        }
        if(SharedPref.getLocationProfile()!=null){
            binding.editLocationText.text=SharedPref.getLocationProfile()
        }
        if(SharedPref.getNumberProfile()!=null){
            binding.editNumberText.text=SharedPref.getNumberProfile()
        }
        if(SharedPref.getBirthProfile()!=null){
            binding.editBirthText.text=SharedPref.getBirthProfile()
        }
        back()
        showEditTextDialog()
        return binding.root
    }
    fun back(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.mainUserFragment)
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showEditTextDialog(){
        binding.editNameText.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialogLayout = layoutInflater.inflate(R.layout.edit_text,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.edit_text)
            with(builder){

                setPositiveButton("Save"){dialog,wich ->
                    SharedPref.setNameProfile(editText.text.toString())
                    binding.editNameText.text = editText.text.toString()
                }
                setNegativeButton("Cancel"){dialog,wich ->
                    Log.d("Main","Negative")
                }
                setView(dialogLayout)
                show()
            }
        }
        binding.editLocationText.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialogLayout = layoutInflater.inflate(R.layout.edit_text,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.edit_text)
            with(builder){

                setPositiveButton("Save"){dialog,wich ->
                    SharedPref.setLocationProfile(editText.text.toString())
                    binding.editLocationText.text = editText.text.toString()
                }
                setNegativeButton("Cancel"){dialog,wich ->
                    Log.d("Main","Negative")
                }
                setView(dialogLayout)
                show()
            }
        }
        binding.editNumberText.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialogLayout = layoutInflater.inflate(R.layout.edit_number,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.edit_tx_number)
            with(builder){

                setPositiveButton("Save"){dialog,wich ->
                    SharedPref.setNumberProfile(editText.text.toString())
                    binding.editNumberText.text = editText.text.toString()
                }
                setNegativeButton("Cancel"){dialog,wich ->
                    Log.d("Main","Negative")
                }
                setView(dialogLayout)
                show()
            }
        }
        binding.editBirthText.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialogLayout = layoutInflater.inflate(R.layout.edit_birth,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.edit_tx_date)
            with(builder){

                setPositiveButton("Save"){dialog,wich ->
                    SharedPref.setBirthProfile(editText.text.toString())
                    binding.editBirthText.text = editText.text.toString()
                }
                setNegativeButton("Cancel"){dialog,wich ->
                    Log.d("Main","Negative")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

}