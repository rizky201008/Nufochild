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
import com.nufochild.screens.FoodListScreen
import com.nufochild.screens.HomeScreen
import com.nufochild.screens.LoginScreen
import com.nufochild.screens.RegisterScreen
import com.nufochild.screens.SettingScreen
import com.nufochild.screens.SplashScreen
import com.nufochild.ui.theme.NufochildTheme

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object SplashScreen : Destination("splash")
    object Register : Destination("register")
    object Login : Destination("login")
    object Foods : Destination("foods")
    object Setting : Destination("setting")
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
    NavHost(navController = navController, startDestination = Destination.SplashScreen.route) {
        composable(Destination.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Destination.Home.route) {
            HomeScreen(navController)
        }
        composable(Destination.Setting.route) {
            SettingScreen(navController)
        }
        composable(Destination.Login.route) {
            LoginScreen(navController)
        }
        composable(Destination.Register.route) {
            RegisterScreen(navController)
        }
        composable(Destination.Foods.route) {
            FoodListScreen(navController)
        }
    }
}