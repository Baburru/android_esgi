package com.example.esgi_project

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.esgi_project.composants.MovieList
import com.example.esgi_project.ui.theme.Esgi_projectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Esgi_projectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val displayList = remember { mutableStateOf(false) }
    val searchQuery = remember {mutableStateOf("")}

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            TopAppBar(
                title = { Text(text = "Films les mieux notés") }
            )
            TextField(onValueChange = {searchQuery.value = it}, value = searchQuery.value, trailingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "Search icon") })

            Button(onClick = {}) {
                Text(text = "Rechercher")
            }
            Button(onClick = { displayList.value = true }) {
                Text(text = "Afficher la liste")
            }
            if (displayList.value) {
                MovieList()
            }
        }
    }
}