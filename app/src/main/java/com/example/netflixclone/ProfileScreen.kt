package com.example.netflixclone.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.netflixclone.Authentication.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onSignOut: () -> Unit,
    authViewModel: AuthViewModel
) {
    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email ?: "No Email"
    val uid = user?.uid ?: ""

    var username by remember { mutableStateOf("Loading...") }

    LaunchedEffect(uid) {
        Log.d("ProfileScreen", "Fetching username for UID: $uid")
        try {
            if (uid.isNotEmpty()) {
                val snapshot = FirebaseDatabase.getInstance()
                    .getReference("users")
                    .child(uid)
                    .get()
                    .await()

                username = snapshot.child("username").value as? String ?: "No Username"
                Log.d("ProfileScreen", "Snapshot: ${snapshot.value}")
                Log.d("ProfileScreen", "Snapshot children: ${snapshot.children}")
                Log.d("ProfileScreen", "Snapshot username: ${snapshot.child("username").value}")

                username = snapshot.child("username").value as? String ?: "No Username"
            } else {
                username = "No Username"
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile", color = Color.Red) },
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
            )
        }
    ){
        Box(
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Email: $email",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        authViewModel.signOut()
                        onSignOut()
                    }, colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("Sign Out", color = Color.Red)
                }
            }
        }
    }
}
