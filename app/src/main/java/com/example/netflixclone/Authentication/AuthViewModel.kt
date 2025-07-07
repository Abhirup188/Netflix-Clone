package com.example.netflixclone.Authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel:ViewModel() {
    private val auth:FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState:LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()){
            _authState.value = AuthState.Error("Email And Password Can't Be Empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something Went Wrong")
                }
            }
    }
    fun signUp(email:String,password: String){
        if(email.isEmpty()||password.isEmpty()){
            _authState.value = AuthState.Error("Email And Password Can't Be Empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something Went Wrong")
                }
            }
    }
    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

}
sealed class AuthState{
    object Authenticated:AuthState()
    object Unauthenticated:AuthState()
    object Loading:AuthState()
    data class Error(val message:String):AuthState()
}