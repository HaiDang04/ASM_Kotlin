package com.example.asm_kotlin


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screens.Splash.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.Splash.route) {
            WelcomeScreen(navController)
        }
        composable(Screens.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Screens.SignIn.route) {
            LoginScreen(navController)
        }
        composable(Screens.Bottom.route) {
            NavigationLayout(navController)
        }
        composable(Screens.Cart.route) {
            CartScreen(navController)
        }
        composable(Screens.Home.route) {
            ProductGrid(navController)
        }
        composable(Screens.Detail.route) {
            ProductDetailScreen(navController)
        }
        composable(Screens.Notification.route) {
            SuccessScreen(navController)
        }
    }
}