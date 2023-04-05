package com.example.rig.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rig.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpReoo   {



     fun uploadDataToRealtime(user :User  ,email: String, password: String):MutableLiveData<Boolean> {
         val state = MutableLiveData<Boolean>()
         FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener {
                 if (it.isSuccessful) {
                     state.postValue(true)
                     UploadData(user)
                 } else {
                     state.postValue(false)
                     Log.d("error", it.exception?.message + "")
                 }
             }
         return state
     }

    }

     fun UploadData(user :User) {
         user.id = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
    }
