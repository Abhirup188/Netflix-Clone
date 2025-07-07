package com.example.netflixclone.Authentication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.netflixclone.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController,
               onNavigationToLogInPage:()->Unit,
               authViewModel: AuthViewModel
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("")}
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Authenticated -> {
                navController.navigate(Screen.HomeScreen.route)
                val uid = FirebaseAuth.getInstance().currentUser?.uid
                FirebaseDatabase.getInstance()
                    .getReference("users")
                    .child(uid!!)
                    .child("username")
                    .setValue("Abhirup")

            }
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name=it },
            label = { Text(text = "Username", color = Color.Red) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Red,
                focusedTextColor = Color.Red,
                unfocusedTextColor = Color.Red
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Username",
                    tint = Color.Red)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email", color = Color.Red) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Red,
                focusedTextColor = Color.Red,
                unfocusedTextColor = Color.Red
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email",
                    tint = Color.Red)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password", color = Color.Red) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Red,
                focusedTextColor = Color.Red,
                unfocusedTextColor = Color.Red
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password",
                    tint = Color.Red)
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible=!passwordVisible
                }) {
                    Icon(imageVector = if(passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick =
                {
                    authViewModel.signUp(email,password)
                },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                disabledContainerColor = Color.White,
            ),
            enabled = authState.value!=AuthState.Loading
        ) {
            Text(text = "Sign Up", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Already have an account? Log In",
            color = Color.Red,
            modifier = Modifier.clickable {onNavigationToLogInPage()}
        )
    }
}
