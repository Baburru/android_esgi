package com.example.esgi_project

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.AppsOutage
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.esgi_project.composants.MovieList
import com.example.esgi_project.di.appModule
import com.example.esgi_project.pages.WatchLaterScreen
import com.example.esgi_project.ui.theme.Esgi_projectTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin() {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            val navController = rememberNavController()
            Esgi_projectTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = navController,
                        startDestination = "Home"
                    ){
                        composable("Home") { MainScreen(navController) }
                        composable("WatchLater") { WatchLaterScreen(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val displayList = remember { mutableStateOf(false) }
    val searchQuery = remember {mutableStateOf("")}

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            TopAppBar(
                title = { Text(text = "Films les mieux notés", color = Color.White)}, backgroundColor = Color(0xFF3F51B5),
                actions = {
                    IconButton(onClick = {navController.navigate(route = "WatchLater")}) {
                        Icon(Icons.Filled.AccessTimeFilled, contentDescription = "watch later icon")
                    }
                }
            )
            TextField(onValueChange = {searchQuery.value = it}, value = searchQuery.value, trailingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "Search icon") })

            Button(onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5))) {
                Text(text = "Rechercher", color = Color.White)
            }
            Button(onClick = { displayList.value = true }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5))) {
                Text(text = "Afficher la liste", color = Color.White)
            }
            if (displayList.value) {
                MovieList()
            }
        }
    }
}