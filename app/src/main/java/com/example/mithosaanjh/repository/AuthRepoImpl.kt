package com.example.mithosaanjh.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepoImpl(var auth : FirebaseAuth): AuthRepo{
    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                callback(true, "Login Successful")
            }else{
                callback(false, it.exception?.message.toString())
            }
        }
    }
}