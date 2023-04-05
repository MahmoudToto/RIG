package com.example.rig.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rig.Model.User

class SignUpViewModel : ViewModel() {
    val repo = SignUpReoo()


    fun SignUpAndUploadData(user: User, email: String, password: String): MutableLiveData<Boolean> {
       return  repo.uploadDataToRealtime(user , email,password)
    }
}