package com.nufochild

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nufochild.screen.SplashScreen
import com.nufochild.ui.theme.NufochildTheme

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object SplashScreen : Destination("splash")
    object Register : Destination("register")
    object Login : Destination("login")
    object Foods : Destination("foods")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NufochildTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationAppHosts(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationAppHosts(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable(Destination.SplashScreen.route) { SplashScreen(navController) }
    }
}